package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button bRender;
    public ImageView imgRender;
    public TextField tVideoSource;
    public Button bAddVideoSource;
    public TextField tAudioSource;
    public Button bAddSourceAudio;
    public WebView webView;
    public VBox vSnapShot;
    public Button bZoomOut;
    public Slider slZoom;
    public Button bZoomIn;
    public ComboBox<Resolutions> cmbResolution;
    public Button bChRes;
    public MediaView mediaMovie;
    public ImageView img;
    public Button bPlay;
    public StackPane stPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cmbResolution.setConverter(new StringConverter<Resolutions>() {
            @Override
            public String toString(Resolutions object) {
                return object.getWidth() + "x" + object.getHeight();
            }

            @Override
            public Resolutions fromString(String string) {
                return null;
            }
        });




        slZoom.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue);
                vSnapShot.setScaleX(slZoom.getValue() / 10);
                vSnapShot.setScaleY(slZoom.getValue() / 10);
            }
        });

        setCmbBox();


        cmbResolution.valueProperty().addListener(new ChangeListener<Resolutions>() {
            @Override
            public void changed(ObservableValue<? extends Resolutions> observable, Resolutions oldValue, Resolutions newValue) {
                vSnapShot.resize(newValue.width, newValue.getHeight());

            }
        });
    }

    public void changeResolution(ActionEvent actionEvent) {

    }



    public void setCmbBox(){
        Resolutions res;
        ArrayList<Resolutions> resolutionsArrayList = new ArrayList<Resolutions>();

        int w = 320;
        int h = 160;

        for(int i=0;i <5;i++) {
            res = new Resolutions();
            res.setWidth(w);
            res.setHeight(h);
            resolutionsArrayList.add(res);
            w *=2;
            h *=2;
        }


        ObservableList arr = FXCollections.observableArrayList(resolutionsArrayList);
        cmbResolution.setItems(arr);

    }

    public void renderData(ActionEvent actionEvent) {

        WritableImage snapshot = vSnapShot.snapshot(new SnapshotParameters(), null);
        imgRender.setImage(snapshot);
    }

    public void play(ActionEvent actionEvent) {
        webView.getEngine().load("https://www.youtube.com/watch?v=E9ja2jv4paQ");
        MediaPlayer mp = null;
        mp = new MediaPlayer(new Media(ClassLoader.getSystemResource("test.mp4").toString()));
        mp = new MediaPlayer(new Media("http://iptv.yuvideo.net:4010/udp/232.232.1.133:9003"));
        mp.setAutoPlay(true);
        mediaMovie.setMediaPlayer(mp);
    }


    private class Resolutions{
        int width;
        int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
