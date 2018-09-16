package CountDown;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javafx.animation.AnimationTimer;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Stack;

public class Main extends Application{
    int currpic=0;
    long timeSinceUpdate=0;
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Count Down Clock");
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(true);
        primaryStage.setHeight(540);
        primaryStage.setWidth(1000);


        Canvas canvas= new Canvas(700,100);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        final Calendar c = FileMannager.loadCountdowns().getCalender();
        Calendar now = Calendar.getInstance();
        gc.setFont(new Font("Courier ",65));
        gc.setFill(Color.RED);


        String str = countdownstring(c);


        gc.fillText(
                str,
                Math.round(canvas.getWidth()/2),
                Math.round(canvas.getHeight()/2)
        );

        gc.clearRect(0,0,700,100);
        gc.fillText(
                "test!",
                Math.round(canvas.getWidth()/2),
                Math.round(canvas.getHeight()/2)
        );

        StackPane layout = new StackPane();
        File file = new File("C:\\Users\\jduha\\Desktop\\car still.jpg");
        Image image = new Image(file.toURI().toString());
        ImageView iv = new ImageView(image);
        iv.setPreserveRatio(true);
        iv.setFitWidth(1920);

        layout.getChildren().add(iv);
        primaryStage.show();
        layout.getChildren().addAll(canvas);

        primaryStage.setScene(new Scene(layout));
        String userHomeFolder = System.getProperty("user.home")+ "\\Desktop";
        File countDownSaves = new File(userHomeFolder, "PER Photos");
        Stack<String> results=FileMannager.listFilesForFolder(countDownSaves);

        primaryStage.show();
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime){



                if(c.compareTo(Calendar.getInstance()) == 1){
                    //System.out.println("update");
                    if(timeSinceUpdate+5000<System.currentTimeMillis()) {
                        System.out.println("switched");
                        timeSinceUpdate=System.currentTimeMillis();

                        File file = new File("C:\\Users\\jduha\\Desktop\\PER Photos\\" + results.get(currpic));
                        currpic++;
                        if (currpic > results.size() - 1)
                            currpic = 0;
                        Image image = new Image(file.toURI().toString());
                        iv.setImage(image);
                    }
                    gc.clearRect(0,0,700,100);
                    gc.fillText(
                        countdownstring(c),
                        Math.round(canvas.getWidth()/2),
                        Math.round(canvas.getHeight()/2)
                );

                } else{
                    gc.clearRect(0,0,700,100);
                    gc.fillText(
                            "DEADLINE!",
                            Math.round(canvas.getWidth()/2),
                            Math.round(canvas.getHeight()/2));
                }
            }
        }.start();
    }

    public String countdownstring(Calendar c) {

        Calendar now = Calendar.getInstance();
        int s = c.get(Calendar.SECOND);
        int m = c.get(Calendar.MINUTE);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int mo = c.get(Calendar.MONTH);
        int y = c.get(Calendar.YEAR);

        if (s - now.get(Calendar.SECOND) < 0) {
            m--;
            s += 60;
        }

        if (m - now.get(Calendar.MINUTE) < 0) {
            h--;
            m += 60;
        }

        if (h - now.get(Calendar.HOUR_OF_DAY) < 0) {
            d--;
            h += 24;
        }

        if (d - now.get(Calendar.DAY_OF_MONTH) < 0) {
            mo--;

            switch (mo) {
                case 0:
                    d += 31;
                    break;
                case 1:
                    if (y % 4 == 0) {
                        d += 29;
                        break;
                    } else {
                        d += 28;
                        break;
                    }

                case 2:
                    d += 31;
                    break;
                case 3:
                    d += 30;
                    break;

                case 4:
                    d += 31;
                    break;

                case 5:
                    d += 30;
                    break;

                case 6:
                    d += 31;
                    break;

                case 7:
                    d += 31;
                    break;

                case 8:
                    d += 30;
                    break;

                case 9:
                    d += 31;
                    break;

                case 10:
                    d += 30;
                    break;

                case 11:
                    d += 31;
                    break;
            }
        }
        if (m - now.get(Calendar.MONTH) < 0) {
            y--;
            mo += 12;
        }

        return  Integer.toString(mo - now.get(Calendar.MONTH)) + " : " +
                Integer.toString(d - now.get(Calendar.DAY_OF_MONTH)) + " : " +
                Integer.toString(h - now.get(Calendar.HOUR_OF_DAY)) + " : " +
                Integer.toString(m - now.get(Calendar.MINUTE)) + " : " +
                Integer.toString(s - now.get(Calendar.SECOND));
    }


    public static void main(String[] args) {
        System.out.println(FileMannager.loadCountdowns().getCalender().getTime());
        Application.launch(args);
    }


}

