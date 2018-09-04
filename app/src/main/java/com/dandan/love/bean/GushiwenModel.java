package com.dandan.love.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.dandan.love.utils.StringUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/3 上午11:47
 * Description:
 */
public class GushiwenModel implements Parcelable{

    /**
     * author : 李白
     * chaodai : 唐代
     * cont : 君不见，黄河之水天上来，奔流到海不复回。<br />君不见，高堂明镜悲白发，朝如青丝暮成雪。<br />人生得意须尽欢，莫使金樽空对月。<br />天生我材必有用，千金散尽还复来。<br />烹羊宰牛且为乐，会须一饮三百杯。<br />岑夫子，丹丘生，将进酒，杯莫停。<br />与君歌一曲，请君为我倾耳听。(倾耳听 一作：侧耳听)<br />钟鼓馔玉不足贵，但愿长醉不复醒。(不足贵 一作：何足贵；不复醒 一作：不愿醒/不用醒)<br />古来圣贤皆寂寞，惟有饮者留其名。(古来 一作：自古；惟 通：唯)<br />陈王昔时宴平乐，斗酒十千恣欢谑。<br />主人何为言少钱，径须沽取对君酌。<br />五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。
     * id : 7722
     * idnew : ee16df5673bc
     * langsongAuthor : 蒋伟伟
     * langsongAuthorPY : jiangweiwei
     * name : 将进酒
     * ok : 34262
     * yizhuAuthor : 佚名
     * yizhuCankao : 山东省教学研究室 ．普通高中新课程实验教科书·语文（选修）唐诗宋词选读 ：山东人民出版社 ，2008 ：3-4 ．& 李静 ．唐诗宋词鉴赏 ．北京 ：华文出版社 ，2009 ：60-61 ．
     */

    private String author;
    private String chaodai;
    @SerializedName("cont")
    private String content;
    private int id;
    private String idnew;
    private String langsongAuthor;
    private String langsongAuthorPY;
    private String name;
    private int ok;
    private String yizhuAuthor;
    private String yizhuCankao;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChaodai() {
        return chaodai;
    }

    public void setChaodai(String chaodai) {
        this.chaodai = chaodai;
    }

    public String getContent() {
        String s = StringUtils.removeSymbolContent(content, '（', '）');
        s = StringUtils.removeSymbolContent(s, '(', ')');
        s = s.replace("<p>", "");
        s = s.replace("</p>", "");
        s = s.replace("<br />", "\n");
        return s;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdnew() {
        return idnew;
    }

    public void setIdnew(String idnew) {
        this.idnew = idnew;
    }

    public String getLangsongAuthor() {
        return langsongAuthor;
    }

    public void setLangsongAuthor(String langsongAuthor) {
        this.langsongAuthor = langsongAuthor;
    }

    public String getLangsongAuthorPY() {
        return langsongAuthorPY;
    }

    public void setLangsongAuthorPY(String langsongAuthorPY) {
        this.langsongAuthorPY = langsongAuthorPY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public String getYizhuAuthor() {
        return yizhuAuthor;
    }

    public void setYizhuAuthor(String yizhuAuthor) {
        this.yizhuAuthor = yizhuAuthor;
    }

    public String getYizhuCankao() {
        return yizhuCankao;
    }

    public void setYizhuCankao(String yizhuCankao) {
        this.yizhuCankao = yizhuCankao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.chaodai);
        dest.writeString(this.content);
        dest.writeInt(this.id);
        dest.writeString(this.idnew);
        dest.writeString(this.langsongAuthor);
        dest.writeString(this.langsongAuthorPY);
        dest.writeString(this.name);
        dest.writeInt(this.ok);
        dest.writeString(this.yizhuAuthor);
        dest.writeString(this.yizhuCankao);
    }

    public GushiwenModel() {
    }

    protected GushiwenModel(Parcel in) {
        this.author = in.readString();
        this.chaodai = in.readString();
        this.content = in.readString();
        this.id = in.readInt();
        this.idnew = in.readString();
        this.langsongAuthor = in.readString();
        this.langsongAuthorPY = in.readString();
        this.name = in.readString();
        this.ok = in.readInt();
        this.yizhuAuthor = in.readString();
        this.yizhuCankao = in.readString();
    }

    public static final Creator<GushiwenModel> CREATOR = new Creator<GushiwenModel>() {
        @Override
        public GushiwenModel createFromParcel(Parcel source) {
            return new GushiwenModel(source);
        }

        @Override
        public GushiwenModel[] newArray(int size) {
            return new GushiwenModel[size];
        }
    };
}
