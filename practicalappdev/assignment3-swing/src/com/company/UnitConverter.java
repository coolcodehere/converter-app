/*
UnitConverter

A class containing conversion rates to be multiplied from for each unit.
 */

package com.company;

import java.util.HashMap;

public class UnitConverter {
    private String mode;

    public UnitConverter(String mode) {
        this.mode = mode;
    }

    /*
    convert

    Arguments
    fromUnit: A string representing the unit being converted FROM
    input: The input value being converted FROM

    Returns: A hashmap containing keys of unit names mapped to their converted values.

    Contains conversion information for weight and distance conversions.
    */
    public HashMap<String, Double> convert(String fromUnit, double input) {
            Double[] conversions = null;
            String[] units = null;
            HashMap<String, Double> ret = new HashMap<>();
            HashMap<String, Double[]> distanceConversions = new HashMap<>();
            HashMap<String, Double[]> weightConversions = new HashMap<>();

            distanceConversions.put("Centimeters", new Double[] {1.0, 0.01, 0.0001, 0.39370078740157, 0.032808398950131,
                    0.0000062137119223733});
            distanceConversions.put("Meters", new Double[] {100.0, 1.0, 0.001, 39.370078740157, 3.2808398950131,
                    0.00062137119223733});
            distanceConversions.put("Kilometers", new Double[]{100000.0, 1000.0, 1.0, 39370.078740157, 3280.8398950131,
                    0.62137119223733});
            distanceConversions.put("Inches", new Double[] {2.54, 0.0254, 0.0000254, 1.0, 0.083333333333333,
                    0.000015782828282828});
            distanceConversions.put("Feet", new Double[] {30.48, 0.3048, 0.0003048, 12.0, 1.0,
                    0.00018939393939394});
            distanceConversions.put("Miles", new Double[] {160934.4, 1609.344, 1.609344, 63360.0, 5280.0, 1.0});

            weightConversions.put("Grams", new Double[] {1.0, 0.001, 0.03527396194958, 0.002204622621849,
                    0.0001574730444178, Double.parseDouble("1.67442999972e-28")});
            weightConversions.put("Kilograms", new Double[] {1000.0, 1.0, 35.27396194958, 2.204622621849,
                    0.1574730444178, Double.parseDouble("1.67443e-25")});
            weightConversions.put("Ounces", new Double[] {28.349523125, 0.028349523125, 1.0, 0.0625, 0.004464285714286,
                    Double.parseDouble("4.746929199825589502e-27")});
            weightConversions.put("Pounds", new Double[] {453.59237, 0.45359237, 16.0, 1.0, 0.07142857142857,
                    Double.parseDouble("7.595086719720943203e-26")});
            weightConversions.put("Stone", new Double[] {6350.29318, 6.35029318, 224.0, 14.0, 1.0,
                    Double.parseDouble("1.063312140760932094e-24")});
            weightConversions.put("Earths", new Double[] {Double.parseDouble("5.972e+27"),
                    Double.parseDouble("5.97199999999999925e+24"),
                    Double.parseDouble("2.10656100762894193e+26"), Double.parseDouble("1.31660062976808871e+25"),
                    Double.parseDouble("9.4042902126292041e+23"), 1.0});

            if (mode.equals("Distance")) {
                units = new String[] {"Centimeters", "Meters", "Kilometers", "Inches", "Feet", "Miles"};
                conversions = distanceConversions.get(fromUnit);
            } else if (mode.equals("Weight")) {
                units = new String[] {"Grams", "Kilograms", "Ounces", "Pounds", "Stone", "Earths"};
                conversions = weightConversions.get(fromUnit);
            }

            for (int i = 0; i < conversions.length; i++) {
                ret.put(units[i], conversions[i] * input);
            }

            return ret;
    }
}
