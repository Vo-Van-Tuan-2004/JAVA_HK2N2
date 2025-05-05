// package util;

// import java.io.IOException;
// import java.io.InputStream;
// import java.util.Properties;

// public class DatabaseConfig {
//     private static final Properties properties = new Properties();

//     static {
//         try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
//             if (input == null) {
//                 throw new IOException("Không tìm thấy file config.properties");
//             }
//             properties.load(input);
//         } catch (IOException e) {
//             throw new RuntimeException("Lỗi khi tải file config.properties: " + e.getMessage(), e);
//         }
//     }

//     public static String getDbUrl() {
//         return properties.getProperty("db.url");
//     }

//     public static String getDbUser() {
//         return properties.getProperty("db.user");
//     }

//     public static String getDbPassword() {
//         return properties.getProperty("db.password");
//     }

//     public static String getDbDriver() {
//         return properties.getProperty("db.driver");
//     }
// }