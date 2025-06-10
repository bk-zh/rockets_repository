package com.six.assignment.spacex.rocket.repository.domain.mission;

 public enum StatusMissionEnum {

     SCHEDULED("Scheduled"),
     PENDING("Pending"),
     IN_PROGRESS("In progress"),
     ENDED("Ended");

     private String printedValue;

     StatusMissionEnum(String printedValue) {
         this.printedValue = printedValue;
     }

     public String getPrintedValue() {
         return printedValue;
     }
 }