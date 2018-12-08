using System;
using System.Collections.Generic;
using System.IO;

namespace OpsHelper.Util.Config
{
    class PropsConfig
    {
        #region 成员变量

        private Dictionary<String, String> props = new Dictionary<String, String>(10);

        #endregion


        public PropsConfig(){
            String filename = @"C:\Users\55117\.opshelper\temp.txt";

            loadPropsConfig(filename);
        }

        private bool loadPropsConfig(String filename)
        {
            using (StreamReader reader = new StreamReader(filename))
            {
                String temp = "";

                do
                {
                    if (String.Empty != temp)
                    {
                        Pair entry = PropsConfig.parsePair(temp, "=");
                        if (!entry.isEmpty())
                        {
                            this.props.Add(entry.getKey(), entry.getValue());
                        }
                    }

                    temp = reader.ReadLine();
                } while (temp != null);
            }

            return true;
        }

        private static Pair parsePair(String src, String gene)
        {
            int index = src.IndexOf(gene);

            if (index >= 0)
            {
                String key = src.Substring(0, index).Trim();
                String value = src.Substring(index, src.Length - index).Trim();
                return new Pair(key, value);
            }

            return new Pair("", "");
        }

        private String getProp(String key)
        {
            if (null == key)
            {
                throw new NullReferenceException();
            }

            if (this.props.ContainsKey(key))
            {
                return this.props[key];
            }
            else
            {
                return "";
            }

        }

        private PropsConfig setProps(String key, String value)
        {
            if(null == key)
            {
                throw new NullReferenceException();
            }

            if(String.Empty != key)
            {
                if (this.props.ContainsKey(key))
                {
                    this.props[key] = value;
                }
                else
                {
                    this.props.Add(key, value);
                }
            }

            return this;
        }
    }
}
