package com.plekhanov.constant.servlet.avatar;

public class AvatarConstants {
    public static final String AVATAR_DIRECTORY= "src/main/webapp/img/avatars/";
    public static final String IMG_AVATAR = "<img class =\"avatar\"src=\"/shop/avatar.do\" alt=\"avatar not found\" />";
    public static final String CHOOSE_FILE = " <form enctype=\"multipart/form-data\" action=\"/shop/avatar.do\" method=\"post\">" +
            " <div  class=\"custom-file\">" +
            "    <input type=\"file\" name=\"avatarFile\"  onchange=\"this.form.submit()\" id=\"inputGroupFile01\">" +
            "    <label class=\"custom-file-label\" for=\"inputGroupFile01\"></label>" +
            "  </div>" +
            "</form>";

    public static final String JPG = "jpg";
    public static final String DOT_JPG = ".jpg";
    public static final String AVATAR_FILE ="avatarFile";
    public static final String DIV_OPEN = "<div >";
    public static final String DIV_CLOSE = "</div>";
}
