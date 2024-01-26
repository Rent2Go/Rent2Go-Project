package com.example.rent2gojavaproject.core.utilities.constants;

public enum EmailConstants {
    EMAIL_ENCODING("UTF-8"),
    EMAIL_FROM("noreply@rentogo.com.tr"),
    EMAIL_FROM_NAME("Rent2Go Company"),
    EMAIL_VERIFICATION_SUBJECT("Email Verification"),
    EMAIL_RESET_PASSWORD_SUBJECT("Password Reset Request"),
    EMAIL_VERIFICATION_HTML_CONTENT("<html>" +
            "<html>" +
            "<body style='font-family: \"Roboto\", sans-serif; margin:0; padding:0;'>" +
            "<div>" +
            "<div style='background-color: white; border-radius: 5px; padding: 5px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);'>" +
            "<h1 style='color: #445566; text-align:left;'>Welcome to Rent2Go!</h1>" +
            "<p>Dear %s,</p>" +
            "<p>Thank you for registering with us. To complete your registration, please click the button below:</p>" +
            "<div style='text-align:left; margin-bottom:20px;'>" +
            "<a href=\"%s\" style='background-color: #5D9CEC; color: white; text-decoration: none; padding: 10px 20px; border-radius:5px; box-shadow: 0px 3px 6px rgba(0,0,0,0.1); display: inline-block;'>Verify Your Email</a>" +
            "</div>" +
            "<p>If the button doesn't work, you can also copy and paste the following link into your web browser:</p>" +
            "<p><a href=\"%s\" style='color:#5D9CEC'>%s</a></p>" +
            "<p>Once your email is verified, you'll be able to start browsing our vast selection of vehicles and make your first reservation. We're excited to have you on board!</p>" +
            "<p style='border-top: 1px solid #DDDDDD; padding-top:20px; color:#888888'>Best Regards,<br>The Rent2Go Team</p>" +
            "</div>" +
            "</div>" +
            "</body>" +
            "</html>"
    ),
    EMAIL_VERIFICATION_TEXT_CONTENT(
            "Dear %s,\n" +
                    "Thank you for registering with us. To complete your registration, please visit the following URL:\n" +
                    "%s\n" +
                    "Once your email is verified, you'll be able to start browsing our vast selection of vehicles and make your first reservation. We're excited to have you on board!\n" +
                    "Best Regards,\nThe Rent2Go Team"
    ),

    EMAIL_RESET_PASSWORD_HTML_CONTENT(
            "<html>" +
                    "<body style='font-family: \"Roboto\", sans-serif; margin:0; padding:0;'>" +
                    "<div>" +
                    "<div style='background-color: white; border-radius: 5px; padding: 20px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);'>" +
                    "<h1 style='color: #445566; text-align:left;'>Rent2Go Password Reset</h1>" +
                    "<p>Dear %s,</p>" +
                    "<p>You have requested to reset your password. Please click the button below to set a new password:</p>" +
                    "<div style='text-align:left; margin-bottom:20px;'>" +
                    "<a href=\"%s\" style='background-color: #5D9CEC; color: white; text-decoration: none; padding: 10px 20px; border-radius:5px; box-shadow: 0px 3px 6px rgba(0,0,0,0.1); display: inline-block;'>Reset Password</a>" +
                    "</div>" +
                    "<p>If the button doesn't work, you can also copy and paste the following link into your web browser:</p>" +
                    "<p><a href=\"%s\" style='color:#5D9CEC'>%s</a></p>" +
                    "<p>If you did not request a password reset, please ignore this email.</p>" +
                    "<p style='border-top: 1px solid #DDDDDD; padding-top:20px; color:#888888'>Best Regards,<br>The Rent2Go Team</p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>"
    ),
    EMAIL_RESET_PASSWORD_TEXT_CONTENT(
            "Dear %s,\n" +
                    "You have requested to reset your password. Please visit the following URL to set a new password:\n" +
                    "%s\n" +
                    "If you did not request a password reset, please ignore this email.\n" +
                    "Best Regards,\nThe Rent2Go Team"
    );

    private final String value;

    EmailConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
