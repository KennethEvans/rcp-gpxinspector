package net.kenevans.gpxinspector.utils.find;

import net.kenevans.gpxinspector.utils.GpxUtils;

/*
 * Created on Sep 6, 2010
 * By Kenneth Evans, Jr.
 */

/**
 * FindNearOptions holds option for finding trackpoints, waypoints, or route
 * waypoints within a radois of a given latitude and longiture.
 * 
 * @author Kenneth Evans, Jr.
 */
public class FindNearOptions
{
    /**
     * Units is an enum to hold the name of a unit and a factor to convert the
     * unit to meters.
     * 
     * @author Kenneth Evans, Jr.
     */
    public static enum Units {
        UNSPECIFIED("unspecified", 0), FEET("ft", 1. / GpxUtils.M2FT), MILES(
            "mi", 1. / GpxUtils.M2MI), METERS("m", 1.), KILOMETERS("km", 1000.);
        private final String name;
        private final double factor;

        /**
         * Units constructor
         * 
         * @param name The name of the unit, e.g. "mi".
         * @param factor The factor to convert the unit to meters.
         */
        Units(String name, double factor) {
            this.name = name;
            this.factor = factor;
        }

        /**
         * Gets the value of the name of these units.
         * 
         * @return
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the value of the given radius in meters.
         * 
         * @param radius The radius in these units.
         * @return
         */
        public double radiusInMeters(double radius) {
            return factor * radius;
        }
    };

    /**
     * A list of the possible (specified) DistanceUnits. Used for looping over
     * the types. Perhaps there is a better way.
     */
    private static final Units[] unitTypes = {Units.FEET, Units.MILES,
        Units.METERS, Units.KILOMETERS};

    public static final double DEFAULT_RADIUS = 1.;
    public static final Units DEFAULT_UNITS = Units.MILES;

    public static enum InputType {
        DIRNAME, GPXFILESET, GPXFILE,
    };

    private double latitude = Double.NaN;
    private double longitude = Double.NaN;
    private double radius = DEFAULT_RADIUS;
    private Units units = DEFAULT_UNITS;
    private InputType inputType = InputType.DIRNAME;
    private String dirName = null;
    private boolean doGpsl = true;
    private boolean doGpx = true;
    private boolean doWpt = true;
    private boolean doTrk = true;
    private boolean trim = false;

    private String filter = "*.*";

    /**
     * @return The value of dirSpecified.
     */
    public boolean isDirSpecified() {
        return dirName != null;
    }

    /**
     * @return The value of latSpecified.
     */
    public boolean isLatSpecified() {
        return latitude != Double.NaN;
    }

    /**
     * @return The value of lonSpecified.
     */
    public boolean isLonSpecified() {
        return longitude != Double.NaN;
    }

    /**
     * @return The value of latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The new value for latitude.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The value of longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The new value for longitude.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return The value of inputType.
     */
    public InputType getInputType() {
        return inputType;
    }

    /**
     * @param inputType The new value for inputType.
     */
    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    /**
     * @return The value of dirName.
     */
    public String getDirName() {
        return dirName;
    }

    /**
     * @param dirName The new value for dirName.
     */
    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    /**
     * @return The value of radius.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius The new value for radius.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return The value of units.
     */
    public Units getUnits() {
        return units;
    }

    /**
     * @param units The new value for units.
     */
    public void setUnits(String unitsString) {
        units = Units.UNSPECIFIED;
        for(Units unit : unitTypes) {
            if(unitsString.equalsIgnoreCase(unit.getName())) {
                units = unit;
                return;
            }
        }
    }

    /**
     * @param units The new value for units.
     */
    public void setUnits(Units units) {
        this.units = units;
    }

    /**
     * @return The value of doGpsl.
     */
    public boolean getDoGpsl() {
        return doGpsl;
    }

    /**
     * @param doGpsl The new value for doGpsl.
     */
    public void setDoGpsl(boolean doGpsl) {
        this.doGpsl = doGpsl;
    }

    /**
     * @return The value of doGpx.
     */
    public boolean getDoGpx() {
        return doGpx;
    }

    /**
     * @param doGpx The new value for doGpx.
     */
    public void setDoGpx(boolean doGpx) {
        this.doGpx = doGpx;
    }

    /**
     * @return The value of unittypes.
     */
    public static Units[] getUnitTypes() {
        return unitTypes;
    }

    /**
     * @return The value of doWpt.
     */
    public boolean getDoWpt() {
        return doWpt;
    }

    /**
     * @param doWpt The new value for doWpt.
     */
    public void setDoWpt(boolean doWpt) {
        this.doWpt = doWpt;
    }

    /**
     * @return The value of doTrk.
     */
    public boolean getDoTrk() {
        return doTrk;
    }

    /**
     * @param doTrk The new value for doTrk.
     */
    public void setDoTrk(boolean doTrk) {
        this.doTrk = doTrk;
    }

    /**
     * @return The value of trim.
     */
    public boolean getTrim() {
        return trim;
    }

    /**
     * @param trim The new value for trim.
     */
    public void setTrim(boolean trim) {
        this.trim = trim;
    }

    /**
     * @return The value of filter.
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param filter The new value for filter.
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

}
