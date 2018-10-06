package jstest;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
    // 收件人电子邮箱
    public static final String to = "zhanglingchun@huawei.com";

    // 发件人电子邮箱
    public static final String from = "13066011905@163.com";

    // 指定发送邮件的主机为 localhost
    public static final String host = "smtp.163.com";

    public static void main(String[] args) {
        // 获取系统属性
        Properties props = System.getProperties();

        // 设置邮件服务器
        props.setProperty("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.user", "13066011905@163.com");
        props.setProperty("mail.password", "lunar0319");

        for (int i = 1; i <= 23; i++) {
            send("G:\\Book\\13010684.part22 (" + i + ").rar", props);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean send(String filename, Properties props) {
        Authenticator authenticator = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("13066011905@163.com", "lunar0319"); // 发件人邮件用户名、密码
            }
        };

        // 获取默认的 Session 对象。
        // Session session = Session.getDefaultInstance(properties);
        Session session = Session.getDefaultInstance(props, authenticator);

        try {
            // 创建默认的 MimeMessage 对象。
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头字段
            message.setSubject("This is the Subject Line!");

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();

            // 消息
            messageBodyPart.setText("This is message body" + filename);

            // 创建多重消息
            Multipart multipart = new MimeMultipart();

            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            // 附件部分
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // 发送完整消息
            message.setContent(multipart);

            // 发送消息
            Transport.send(message);

            System.out.println("Sent message and file " + filename + " successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return true;
    }
}
