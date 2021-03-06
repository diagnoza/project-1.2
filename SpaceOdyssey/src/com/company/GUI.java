package com.company;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUI implements Initializable {
    @FXML
    private NumberAxis yAxis;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private LineChart<?, ?> chart;
    @FXML
    private NumberAxis yAxis1;
    @FXML
    private NumberAxis xAxis1;
    @FXML
    private LineChart<?, ?> chart1;
    @FXML
    private NumberAxis yAxis2;
    @FXML
    private NumberAxis xAxis2;
    @FXML
    private LineChart<?, ?> chart2;
    @FXML
    private NumberAxis yAxis3;
    @FXML
    private NumberAxis xAxis3;
    @FXML
    private LineChart<?, ?> chart3;
    @FXML
    private NumberAxis yAxis4;
    @FXML
    private NumberAxis xAxis4;
    @FXML
    private LineChart<?, ?> chart4;
    @FXML
    private NumberAxis yAxis5;
    @FXML
    private NumberAxis xAxis5;
    @FXML
    private LineChart<?, ?> chart5;


    private ArrayList positionX = new ArrayList();
    private ArrayList positionY = new ArrayList();
    private ArrayList velocityX = new ArrayList();
    private ArrayList velocityY = new ArrayList();
    private ArrayList accelerationX = new ArrayList();
    private ArrayList accelerationY = new ArrayList();
    private ArrayList dragDecelerationX = new ArrayList();
    private ArrayList dragDecelerationY = new ArrayList();
    private ArrayList thrustX = new ArrayList();
    private ArrayList thrustY = new ArrayList();
    private ArrayList theta = new ArrayList();
    private ArrayList rotSpeed = new ArrayList();
    private ArrayList torque = new ArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            readData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
	    XYChart.Series seriesVelocityX = new XYChart.Series();
        XYChart.Series seriesVelocityY = new XYChart.Series();
        XYChart.Series seriesAccelX = new XYChart.Series();
        XYChart.Series seriesAccelY = new XYChart.Series();
        XYChart.Series seriesDragDecelX = new XYChart.Series();
        XYChart.Series seriesDragDecelY = new XYChart.Series();
        XYChart.Series seriesTheta = new XYChart.Series();
        XYChart.Series seriesRotSpeed = new XYChart.Series();
        XYChart.Series seriesTorque = new XYChart.Series();
        XYChart.Series seriesThrustX = new XYChart.Series();
        XYChart.Series seriesThrustY = new XYChart.Series();

        for(int i = 2500; i < positionY.size(); i++) {
            /*Take every 1000th data point, so we don't end up with a graph with an ugly line
             * that's made out of all the data points (replace 1000 with 10 or 100 to see my point)*/
            if (i % 50 == 0) {
                /*In this case instead of reading timestep from the file, we can simply use i,
                 * it behaves the same way, since it is also incremented by 1 every iteration.
                 * Same would apply to any other series that takes time as one of the vars.*/
                series.getData().add(new XYChart.Data(i, positionX.get(i)));
                series1.getData().add(new XYChart.Data(i, positionY.get(i)));
                seriesVelocityX.getData().add(new XYChart.Data(i, velocityX.get(i)));
                seriesVelocityY.getData().add(new XYChart.Data(i, velocityY.get(i)));
                seriesAccelX.getData().add(new XYChart.Data(i, accelerationX.get(i)));
                seriesAccelY.getData().add(new XYChart.Data(i, accelerationY.get(i)));
                seriesDragDecelX.getData().add(new XYChart.Data(i, dragDecelerationX.get(i)));
                seriesDragDecelY.getData().add(new XYChart.Data(i, dragDecelerationY.get(i)));
                seriesThrustX.getData().add(new XYChart.Data(i, thrustX.get(i)));
                seriesThrustY.getData().add(new XYChart.Data(i, thrustY.get(i)));
                seriesTheta.getData().add(new XYChart.Data(i, theta.get(i)));
                seriesRotSpeed.getData().add(new XYChart.Data(i, rotSpeed.get(i)));
                seriesTorque.getData().add(new XYChart.Data(i, torque.get(i)));
		
            }
        }

        for(int i = 2500; i < positionY.size(); i++)
            if (i % 300 == 0) {

            }

        series.setName("X coordinate");
        series1.setName("Y coordinate");
        chart.setTitle("POSITION OVER TIME");
        xAxis.setLabel("Time");
        yAxis.setLabel("Position");
        chart.getData().addAll(series);
        chart.getData().addAll(series1);

        seriesVelocityX.setName("Vx");
        seriesVelocityY.setName("Vy");
        chart1.setTitle("VELOCITY OVER TIME");
        xAxis1.setLabel("Time");
        yAxis1.setLabel("Velocity");
        chart1.getData().addAll(seriesVelocityX);
        chart1.getData().addAll(seriesVelocityY);

	    seriesAccelX.setName("Ax");
	    seriesAccelY.setName("Ay");
        chart2.setTitle("ACCELERATION OVER TIME");
        xAxis2.setLabel("Time");
        yAxis2.setLabel("Acceleration");
        chart2.getData().addAll(seriesAccelX);
        chart2.getData().addAll(seriesAccelY);

	    seriesTorque.setName("Newtonmetres");
	    seriesRotSpeed.setName("Radians/second");
	    seriesTheta.setName("Radians");
        chart3.setTitle("TORQUE OVER TIME");
        xAxis3.setLabel("Time");
        yAxis3.setLabel("Torque");
        chart3.getData().addAll(seriesTorque);
        chart3.getData().addAll(seriesRotSpeed);
        chart3.getData().addAll(seriesTheta);

	    seriesThrustX.setName("???");
	    seriesThrustY.setName("???");
        chart4.setTitle("THRUST OVER TIME");
        xAxis4.setLabel("Time");
        yAxis4.setLabel("Thrust");
        chart4.getData().addAll(seriesThrustX);
        chart4.getData().addAll(seriesThrustY);

        seriesDragDecelX.setName("???");
        seriesDragDecelY.setName("???");
        chart5.setTitle("DRAG DECELERATION OVER TIME");
        xAxis5.setLabel("Time");
        yAxis5.setLabel("Drag Deceleration");
        chart5.getData().addAll(seriesDragDecelX);
        chart5.getData().addAll(seriesDragDecelY);
    }



    public void readData() throws Exception{
        File file = new File("tmp.data");
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            if (st.contains("Position")) {
                String[] pointToAdd = st.split(" ");
                positionX.add(Double.parseDouble(pointToAdd[3]));
                positionY.add(Double.parseDouble(pointToAdd[6]));
            }
            if (st.contains("Velocity")) {
                String[] pointToAdd = st.split(" ");
                velocityX.add(Double.parseDouble(pointToAdd[3]));
                velocityY.add(Double.parseDouble(pointToAdd[6]));
            }
            if (st.contains("Acceleration")) {
                String[] pointToAdd = st.split(" ");
                accelerationX.add(Double.parseDouble(pointToAdd[3]));
                accelerationY.add(Double.parseDouble(pointToAdd[6]));
            }
            if (st.contains("DragDeceleration")) {
                String[] pointToAdd = st.split(" ");
                dragDecelerationX.add(Double.parseDouble(pointToAdd[3]));
                dragDecelerationY.add(Double.parseDouble(pointToAdd[6]));
            }

            if (st.contains("Thrust")) {
                String[] pointToAdd = st.split(" ");
                thrustX.add(Double.parseDouble(pointToAdd[3]));
                thrustY.add(Double.parseDouble(pointToAdd[6]));
            }
            if (st.contains("Theta")) {
                String[] pointToAdd = st.split(" ");
                theta.add(Double.parseDouble(pointToAdd[2]));

            }
	        if (st.contains("Torque")) {
                String[] pointToAdd = st.split(" ");
                torque.add(Double.parseDouble(pointToAdd[2]));
            }
	        if (st.contains("RotSpeed")) {
                String[] pointToAdd = st.split(" ");
                rotSpeed.add(Double.parseDouble(pointToAdd[2]));
            }


        }
    }


}
