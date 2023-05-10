/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.services.serviceReservation;

/**
 *
 * @author kortb
 */
public class ReservationChartService {
  
   public void showChart() {
    Map<String, Object> responseData = serviceReservation.getInstance().getChartData();

    if (responseData != null) {
        List<List<Object>> root = (List<List<Object>>) responseData.get("root");
        List<Object[]> chartData = new ArrayList<>();
        chartData.add(new Object[] { "Film", "Reservation Percentage" });

        // Skip the first element in each inner list
        for (int i = 1; i < root.size(); i++) {
            List<Object> item = root.get(i);
            String filmName = (String) item.get(0);
            float reservationPercentage = Float.parseFloat(item.get(1).toString());
            chartData.add(new Object[] { filmName, reservationPercentage });
        }

        // Render the chart
        renderChart(chartData);
    }
}

public void renderChart(List<Object[]> chartData) {
    // Create the chart series
     CategorySeries series = new CategorySeries("Reservation Percentage");

    // Start from index 1 to skip the header row
    for (int i = 1; i < chartData.size(); i++) {
        Object[] data = chartData.get(i);
        String filmName = (String) data[0];
        float reservationPercentage = Float.parseFloat(data[1].toString());
        series.add(filmName, reservationPercentage);
    }

    // Create the chart renderer
    DefaultRenderer renderer = new DefaultRenderer();
    int[] colors = new int[] { ColorUtil.MAGENTA, ColorUtil.CYAN, ColorUtil.YELLOW, ColorUtil.GREEN, ColorUtil.BLUE };

    for (int i = 0; i < series.getItemCount(); i++) {
        SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
        seriesRenderer.setColor(colors[i % colors.length]);
        renderer.addSeriesRenderer(seriesRenderer);
    }

    renderer.setLabelsColor(ColorUtil.WHITE);
    renderer.setLabelsTextSize(18);
    renderer.setLegendTextSize(18);

    CategorySeries updatedSeries = new CategorySeries("Reservation Percentage");

    for (int i = 0; i < series.getItemCount(); i++) {
        String filmName = series.getCategory(i);
        String formattedFilmName = "<html><font size='16'>" + filmName + "</font></html>";
        double reservationPercentage = series.getValue(i);
        updatedSeries.add(formattedFilmName, reservationPercentage);
    }
    
    
    // Create the chart component
    ChartComponent chartComponent = new ChartComponent(createPieChart(series, renderer));

    // Show the chart in a form
    Form chartForm = new Form("Reservation Chart");
    chartForm.setLayout(new BorderLayout());
    chartForm.add(BorderLayout.CENTER, chartComponent);
    chartForm.show();
}

private PieChart createPieChart(CategorySeries series, DefaultRenderer renderer) {
    return new PieChart(series, renderer);
}

    
    
}
