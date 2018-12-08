using System;

namespace OpsHelper.Util.Config
{
    class Pair
    {
        #region 成员变量

        private String key = "";

        private String value = "";

        private bool empty = true;

        #endregion

        public Pair(String key, String value)
        {
            if (null == key || null == value)
            {
                throw new NullReferenceException();
            }

            if (String.Empty != key)
            {
                this.key = key;
                this.value = value;
                this.empty = false;
            }
        }

        public Pair setKey(String key)
        {
            if (null == key)
            {
                throw new NullReferenceException();
            }

            this.key = key;

            if (String.Empty == key)
            {
                this.value = "";
                this.empty = true;
            }

            return this;
        }

        public String getKey()
        {
            return this.key;
        }

        private Pair setValue(String value)
        {
            if (null == value)
            {
                throw new NullReferenceException();
            }

            this.value = value;

            return this;
        }

        public String getValue()
        {
            return this.value;
        }

        public bool isEmpty()
        {
            return this.empty;
        }
    }
}
