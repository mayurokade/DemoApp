package com.rgi.demoapp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rgi.demoapp.app.BookApp;
import com.rgi.demoapp.utils.IOUtils;

public class BookModel {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


    public class Formats {

        @SerializedName("application/x-mobipocket-ebook")
        @Expose
        private String applicationXMobipocketEbook;
        @SerializedName("application/pdf")
        @Expose
        private String applicationPdf;
        @SerializedName("text/plain; charset=us-ascii")
        @Expose
        private String textPlainCharsetUsAscii;
        @SerializedName("text/plain; charset=utf-8")
        @Expose
        private String textPlainCharsetUtf8;
        @SerializedName("application/rdf+xml")
        @Expose
        private String applicationRdfXml;
        @SerializedName("application/zip")
        @Expose
        private String applicationZip;
        @SerializedName("application/epub+zip")
        @Expose
        private String applicationEpubZip;
        @SerializedName("text/html; charset=utf-8")
        @Expose
        private String textHtmlCharsetUtf8;
        @SerializedName("text/plain; charset=iso-8859-1")
        @Expose
        private String textPlainCharsetIso88591;
        @SerializedName("image/jpeg")
        @Expose
        private String imageJpeg;
        @SerializedName("text/plain")
        @Expose
        private String textPlain;
        @SerializedName("text/html; charset=us-ascii")
        @Expose
        private String textHtmlCharsetUsAscii;
        @SerializedName("text/rtf")
        @Expose
        private String textRtf;
        @SerializedName("text/html; charset=iso-8859-1")
        @Expose
        private String textHtmlCharsetIso88591;
        @SerializedName("text/html")
        @Expose
        private String textHtml;

        public String getApplicationXMobipocketEbook() {
            return applicationXMobipocketEbook;
        }

        public void setApplicationXMobipocketEbook(String applicationXMobipocketEbook) {
            this.applicationXMobipocketEbook = applicationXMobipocketEbook;
        }

        public String getApplicationPdf() {
            return applicationPdf;
        }

        public void setApplicationPdf(String applicationPdf) {
            this.applicationPdf = applicationPdf;
        }

        public String getTextPlainCharsetUsAscii() {
            return textPlainCharsetUsAscii;
        }

        public void setTextPlainCharsetUsAscii(String textPlainCharsetUsAscii) {
            this.textPlainCharsetUsAscii = textPlainCharsetUsAscii;
        }

        public String getTextPlainCharsetUtf8() {
            return textPlainCharsetUtf8;
        }

        public void setTextPlainCharsetUtf8(String textPlainCharsetUtf8) {
            this.textPlainCharsetUtf8 = textPlainCharsetUtf8;
        }

        public String getApplicationRdfXml() {
            return applicationRdfXml;
        }

        public void setApplicationRdfXml(String applicationRdfXml) {
            this.applicationRdfXml = applicationRdfXml;
        }

        public String getApplicationZip() {
            return applicationZip;
        }

        public void setApplicationZip(String applicationZip) {
            this.applicationZip = applicationZip;
        }

        public String getApplicationEpubZip() {
            return applicationEpubZip;
        }

        public void setApplicationEpubZip(String applicationEpubZip) {
            this.applicationEpubZip = applicationEpubZip;
        }

        public String getTextHtmlCharsetUtf8() {
            return textHtmlCharsetUtf8;
        }

        public void setTextHtmlCharsetUtf8(String textHtmlCharsetUtf8) {
            this.textHtmlCharsetUtf8 = textHtmlCharsetUtf8;
        }

        public String getTextPlainCharsetIso88591() {
            return textPlainCharsetIso88591;
        }

        public void setTextPlainCharsetIso88591(String textPlainCharsetIso88591) {
            this.textPlainCharsetIso88591 = textPlainCharsetIso88591;
        }

        public String getImageJpeg() {
            return imageJpeg;
        }

        public void setImageJpeg(String imageJpeg) {
            this.imageJpeg = imageJpeg;
        }

        public String getTextPlain() {
            return textPlain;
        }

        public void setTextPlain(String textPlain) {
            this.textPlain = textPlain;
        }

        public String getTextHtmlCharsetUsAscii() {
            return textHtmlCharsetUsAscii;
        }

        public void setTextHtmlCharsetUsAscii(String textHtmlCharsetUsAscii) {
            this.textHtmlCharsetUsAscii = textHtmlCharsetUsAscii;
        }

        public String getTextRtf() {
            return textRtf;
        }

        public void setTextRtf(String textRtf) {
            this.textRtf = textRtf;
        }

        public String getTextHtmlCharsetIso88591() {
            return textHtmlCharsetIso88591;
        }

        public void setTextHtmlCharsetIso88591(String textHtmlCharsetIso88591) {
            this.textHtmlCharsetIso88591 = textHtmlCharsetIso88591;
        }

        public String getTextHtml() {
            return textHtml;
        }

        public void setTextHtml(String textHtml) {
            this.textHtml = textHtml;
        }

    }


    public static class Result {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("authors")
        @Expose
        private List<Author> authors = null;
        @SerializedName("bookshelves")
        @Expose
        private List<String> bookshelves = null;
        @SerializedName("download_count")
        @Expose
        private Integer downloadCount;
        @SerializedName("formats")
        @Expose
        private Formats formats;
        @SerializedName("languages")
        @Expose
        private List<String> languages = null;
        @SerializedName("media_type")
        @Expose
        private String mediaType;
        @SerializedName("subjects")
        @Expose
        private List<String> subjects = null;
        @SerializedName("title")
        @Expose
        private String title;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<Author> getAuthors() {
            return authors;
        }

        public void setAuthors(List<Author> authors) {
            this.authors = authors;
        }

        public List<String> getBookshelves() {
            return bookshelves;
        }

        public void setBookshelves(List<String> bookshelves) {
            this.bookshelves = bookshelves;
        }

        public Integer getDownloadCount() {
            return downloadCount;
        }

        public void setDownloadCount(Integer downloadCount) {
            this.downloadCount = downloadCount;
        }

        public Formats getFormats() {
            return formats;
        }

        public void setFormats(Formats formats) {
            this.formats = formats;
        }

        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public List<String> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<String> subjects) {
            this.subjects = subjects;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    public class Author {

        @SerializedName("birth_year")
        @Expose
        private Integer birthYear;
        @SerializedName("death_year")
        @Expose
        private Integer deathYear;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getBirthYear() {
            return birthYear;
        }

        public void setBirthYear(Integer birthYear) {
            this.birthYear = birthYear;
        }

        public Integer getDeathYear() {
            return deathYear;
        }

        public void setDeathYear(Integer deathYear) {
            this.deathYear = deathYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(/*IOUtils.getRoundedImageTarget(,imageView, radius*/view);
    }

}
