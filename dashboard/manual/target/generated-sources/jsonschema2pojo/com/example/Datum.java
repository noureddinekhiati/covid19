
package com.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "updated_at",
    "date",
    "deaths",
    "confirmed",
    "recovered",
    "active",
    "new_confirmed",
    "new_recovered",
    "new_deaths",
    "is_in_progress"
})
public class Datum {

    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("date")
    private String date;
    @JsonProperty("deaths")
    private Integer deaths;
    @JsonProperty("confirmed")
    private Integer confirmed;
    @JsonProperty("recovered")
    private Integer recovered;
    @JsonProperty("active")
    private Integer active;
    @JsonProperty("new_confirmed")
    private Integer newConfirmed;
    @JsonProperty("new_recovered")
    private Integer newRecovered;
    @JsonProperty("new_deaths")
    private Integer newDeaths;
    @JsonProperty("is_in_progress")
    private Boolean isInProgress;

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("deaths")
    public Integer getDeaths() {
        return deaths;
    }

    @JsonProperty("deaths")
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    @JsonProperty("confirmed")
    public Integer getConfirmed() {
        return confirmed;
    }

    @JsonProperty("confirmed")
    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    @JsonProperty("recovered")
    public Integer getRecovered() {
        return recovered;
    }

    @JsonProperty("recovered")
    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    @JsonProperty("active")
    public Integer getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Integer active) {
        this.active = active;
    }

    @JsonProperty("new_confirmed")
    public Integer getNewConfirmed() {
        return newConfirmed;
    }

    @JsonProperty("new_confirmed")
    public void setNewConfirmed(Integer newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    @JsonProperty("new_recovered")
    public Integer getNewRecovered() {
        return newRecovered;
    }

    @JsonProperty("new_recovered")
    public void setNewRecovered(Integer newRecovered) {
        this.newRecovered = newRecovered;
    }

    @JsonProperty("new_deaths")
    public Integer getNewDeaths() {
        return newDeaths;
    }

    @JsonProperty("new_deaths")
    public void setNewDeaths(Integer newDeaths) {
        this.newDeaths = newDeaths;
    }

    @JsonProperty("is_in_progress")
    public Boolean getIsInProgress() {
        return isInProgress;
    }

    @JsonProperty("is_in_progress")
    public void setIsInProgress(Boolean isInProgress) {
        this.isInProgress = isInProgress;
    }

}
