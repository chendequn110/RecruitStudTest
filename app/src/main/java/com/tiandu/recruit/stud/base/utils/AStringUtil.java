package com.tiandu.recruit.stud.base.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;


import com.tiandu.recruit.stud.base.App;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by jerome on 15-10-19.
 * E-mail kai.zhang15@pactera.com
 */
public class AStringUtil {

    private static final String TAG = AStringUtil.class.getSimpleName();

    public static boolean isLengthBetween(String str, int min, int max) {
        return !TextUtils.isEmpty(str) && str.length() >= min && str.length() <= max;
    }

    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static boolean isEmpty(Object[] objs) {
        return null == objs || objs.length <= 0;
    }

    public static boolean isEmpty(int[] objs) {
        return null == objs || objs.length <= 0;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return null == charSequence || charSequence.length() <= 0;
    }

    public static boolean isBlank(CharSequence charSequence) {
        return null == charSequence || charSequence.toString().trim().length() <= 0;
    }

    public static boolean isLeast(Object[] objs, int count) {
        return null != objs && objs.length >= count;
    }

    public static boolean isLeast(int[] objs, int count) {
        return null != objs && objs.length >= count;
    }

    public static boolean isEquals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static String trim(CharSequence charSequence) {
        return null == charSequence ? null : charSequence.toString().trim();
    }

    public static boolean isLetter(String str) {
        String regEx = "[a-zA-Z]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str.trim());
        return matcher.matches();
    }

    /**
     * 格式化手机号码
     */
    public static String formatPhoneNum(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            return "";
        } else if (!phoneNum.startsWith("+86") && !phoneNum.startsWith("%2b86") && !phoneNum.startsWith("%2B86")) {
            return "+86" + phoneNum;
        } else {
            return phoneNum;
        }
    }

    public static String getPrivacyPhone(String pNumber, TextView mPhoneNumber) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
       return null;
    }

    /**
     * 身份证号验证
     */
    public boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

    /**
     * 匹配 数字、字母、英文符号
     *
     * @param content
     */
    public static boolean isPassword(String content) {
        Pattern pattern = Pattern.compile("^([\\w\\?%&=@#+*$()~`^<>{}!:;,./\\-_]|[\\\\\"\'\\|\\[\\]])+$");
        Matcher matcher = pattern.matcher(content.trim());
        return matcher.matches();
    }

    /**
     * 匹配数字、汉字、字母、下划线，长度在指定范围内
     */
    public static boolean isForContent(String content) {
        Pattern pattern = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_]{0,10}+$");
        Matcher matcher = pattern.matcher(content.trim());
        return matcher.matches();
    }

    public static boolean isPhone(String phone) {
        String telRegex = "^1[34578]\\d{9}$";
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.show("请输入手机号码");
            return false;
        } else {
            return phone.matches(telRegex);
        }
    }

    /**
     * 非法字符过滤
     */
    public static boolean isIllegalFilter(String content) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(content.trim());
        return matcher.matches();
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }


    public static String num2String(Long no) {
        if (no.longValue() < 10L)
            return String.valueOf(no);
        if (no.longValue() < 36L)
            return String.valueOf((char) (int) (no.longValue() - 10L + 65L));
        if (no.longValue() < 62L) {
            return String.valueOf((char) (int) (no.longValue() - 36L + 97L));
        }
        return num2String(Long.valueOf(no.longValue() / 62L)) + num2String(Long.valueOf(no.longValue() % 62L));
    }

    /**
     * 返回￥0.00格式的金额
     */
    public static String moneyFormat(Object money) {
        return String.format("￥%,.2f", money);
    }


    public static String replaceSpace(String info) {
        String str = null;
        if (info != null) {
            Pattern p = Pattern.compile("\\s*|\t|\n|\n");
            Matcher m = p.matcher(info);
            str = m.replaceAll("");
        }
        return str;
    }

    public static boolean isPatternType(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher isRegex = pattern.matcher(str);
        return isRegex.matches();
    }

    /**
     * 转化为半角字符
     */
    public static String toDBC(String string) {
        if (isEmpty(string)) {
            return string;
        }
        char[] chars = string.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     */
    public static String toSBC(String string) {
        if (isEmpty(string)) {
            return string;
        }
        char[] chars = string.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    public static String formatHour(@NonNull String strStart, @NonNull String endStr) {
        strStart = strStart.split(" ")[1];
        if (!AStringUtil.isEmpty(strStart)) {
            if (!AStringUtil.isEmpty(endStr)) {
                endStr = endStr.split(" ")[1];
                return strStart + "-" + endStr;
            } else {
                return strStart + "-";
            }
        }
        return null;
    }

    public static String formatStr(@NonNull String str, @NonNull String endStr) {
        if (!AStringUtil.isEmpty(str)) {
            str = str.substring(str.indexOf("-") + 1);
            str = str.replace("-", "月");
            str = str.replace(" ", "日");
            String[] result = str.split("日");
            if (!AStringUtil.isEmpty(endStr)) {
                endStr = endStr.split(" ")[1];
                return result[0] + "日\t\t" + result[1] + "-" + endStr;
            } else {
                return result[0] + "日\t\t" + result[1] + "-";
            }
        }
        return null;
    }

    public static String formatMonth(@NonNull String str) {
        if (!AStringUtil.isEmpty(str)) {
            str = str.split(" ")[0] + "日";
            str = str.substring(str.indexOf("-") + 1);
            return str.replace("-", "月");
        }
        return null;
    }

    public static int calcSubjectScore(double score,double score2) {
        double countScore = score/(score + score2);
        DecimalFormat df = new DecimalFormat("######0.00");
        int result = 100-(int) (Double.valueOf(df.format(countScore)) * 100);
        return result < 0 ? 100: result;
    }

    public static String formatTimeDetail(@NonNull String str) {
        if (!AStringUtil.isEmpty(str)) {
            str = str.split(" ")[0] + "日";
            str = str.replaceFirst("-", "年");
            return str.replace("-", "月");
        }
        return null;
    }

    public static String formatDate(String str) {
        if (!AStringUtil.isEmpty(str)) {
            str = str.replace(" ","日");
            str = str.replaceFirst("-", "年");
            str = str.substring(5, str.length());
            return str.replace("-", "月");
        }
        return null;
    }

    public static String formatDate(String str, String str2) {
        if (AStringUtil.isEmpty(str))return "";
        if (AStringUtil.isEmpty(str2))return "";
        str = str.substring(0, str.lastIndexOf("."));
        str = str.replace("-","月");
        String[] s = str.split(" ");
        String year = s[0];
        String time1 = s[1].substring(0, s[1].lastIndexOf(":"));
        String s1 = year.substring(5, year.length());

        str2 = str2.substring(0, str2.lastIndexOf("."));
        str2 = str2.replace("-",".");
        String[] ss = str2.split(" ");
        String time2 = ss[1].substring(0, ss[1].lastIndexOf(":"));

        return s1 + "日" + " " + time1 + "-" + time2;
    }

    public static String trainNote(@NonNull String str , @NonNull int sub) {
        String subject = null;

        str = str.substring(str.indexOf("-")+1);
        str = str.replace("-", "月");
        str = str.replace(" ", "日");
        if (sub != 0) {
            str = str.substring(0, str.indexOf("日") + 1);
            switch (sub) {
                case 1:
                    subject = "科目一";
                    break;
                case 21:
                case 22:
                    subject = "科目二";
                    break;
                case 31:
                case 32:
                    subject = "科目三";
                    break;
                case 4:
                    subject = "科目四";
                    break;
            }
        }
        return str+"\t\t"+subject;
    }

    public static String trainNote(@NonNull int sub) {
        String subject = null;
        switch (sub) {
            case 1:
                subject = "科目一";
                break;
            case 21:
            case 22:
                subject = "科目二";
                break;
            case 31:
            case 32:
                subject = "科目三";
                break;
            case 4:
                subject = "科目四";
                break;
        }
        return subject;
    }

    public static boolean trainisNote(@NonNull int sub) {
        switch (sub) {
            case 1:
            case 22:
            case 32:
            case 4:
                return true;
            case 21:
            case 31:
                return false;
        }
        return false;
    }

    public static String trainSub(@NonNull int sub) {
        String subject = null;
        switch (sub) {
            case 1:
                subject = "科目一课堂";
                break;
            case 21:
                subject = "科目二实车";
                break;
            case 22:
                subject = "科目二模拟";
                break;
            case 31:
                subject = "科目三实车";
                break;
            case 32:
                subject = "科目三模拟";
                break;
            case 4:
                subject = "科目四课堂";
                break;
        }
        return subject;
    }


    /**
     * 改变字体color和Typeface
     */
    public static Spannable setColorAndBold(TextView view, String keyWords, String content) {
        int[] index = new int[1];
        view.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        index[0] = content.indexOf(keyWords);
        Spannable style = new SpannableString(content);
        style.setSpan(new ForegroundColorSpan(Color.rgb(128, 189, 0)), index[0], index[0] + 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

    /**
     * 压缩字符串到Zip
     */
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }

    /**
     * 解压Zip字符串
     *
     * @param str
     * @return 解压后字符串
     * @throws IOException
     */
    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(str
                .getBytes("UTF-8"));
        return uncompress(in);
    }


    /**
     * InputStream convert to string
     */
    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    /**
     * 解压Gzip获取
     */
//    public static String inputStream2StringFromGZIP(InputStream is) {
//        StringBuilder resultSb = new StringBuilder();
//        BufferedInputStream bis = null;
//        InputStreamReader reader = null;
//        try {
//            bis = new BufferedInputStream(is);
//            bis.mark(2);
//            // 取前两个字节
//            byte[] header = new byte[2];
//            int result = bis.read(header);
//            // reset输入流到开始位置
//            bis.reset();
//            // 判断是否是GZIP格式
//            int headerData = getShort(header);
//            // Gzip流的前两个字节是0x1f8b
//            if (result != -1 && headerData == 0x1f8b) {
//                is = new GZIPInputStream(bis);
//            } else {
//                is = bis;
//            }
//            reader = new InputStreamReader(is, "utf-8");
//            char[] data = new char[100];
//            int readSize;
//            while ((readSize = reader.read(data)) > 0) {
//                resultSb.append(data, 0, readSize);
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        } finally {
//            AFileUtil.closeIO(is, bis, reader);
//        }
//        return resultSb.toString();
//    }

    /**
     * 解压Zip字符串
     */
    public static String uncompress(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream gunzip = new GZIPInputStream(inputStream);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toString();
    }

    private static int getShort(byte[] data) {
        return (int) ((data[0] << 8) | data[1] & 0xFF);
    }

    /**
     * 数组转集合
     */
    public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }


}
