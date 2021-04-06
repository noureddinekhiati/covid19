package com.example.demo;

import com.example.Datum;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("")
public class DashboardView extends VerticalLayout {
    public DashboardView(CoronavirusService service) {
        List<Datum> data = service.timeline().getData();

        Datum latest = data.get(0);

        add(
        new H1("Coronavirus dashboard"),
        new DashboardNumber("Confirmed", latest.getConfirmed()),
        new DashboardNumber("Deaths", latest.getDeaths()),
        new DashboardNumber("Recovered", latest.getRecovered()),
        new DashboardChart(data, ChartType.SPLINE, "Cumulative",
                Datum::getConfirmed, Datum::getDeaths,
                Datum::getRecovered),
        new DashboardChart(data.subList(0, 7), ChartType.COLUMN, "Daily",
                Datum::getNewConfirmed, Datum::getNewDeaths,
                Datum::getNewRecovered)
        );
    }
}
