package com.six.assignment.spacex.rocket.repository.domain.rocket;

 public enum StatusRocketEnum {
     ON_GROUND("On ground"),
     IN_SPACE("In space"),
     IN_REPAIR("In repair");

     StatusRocketEnum(String printedValue) {
         this.printedValue = printedValue;
     }

     private String printedValue;

     public String getPrintedValue() {
         return printedValue;
     }
 }

