package net.kenevans.gpxinspector.plugin;

import net.kenevans.gpxinspector.utils.GpxUtils;

/*
 * Created on Oct 11, 2010
 * By Kenneth Evans, Jr.
 */

public interface IPluginConstants
{
    public static final int KML_COLOR_MODE_COLOR = 0;
    public static final int KML_COLOR_MODE_COLORSET = 1;
    public static final int KML_COLOR_MODE_RAINBOW = 2;
    public static final String[][] kmlColorModes = {
        {"Color", Integer.toString(KML_COLOR_MODE_COLOR)},
        {"Color Set", Integer.toString(KML_COLOR_MODE_COLORSET)},
        {"Rainbow", Integer.toString(KML_COLOR_MODE_RAINBOW)},};

    public static enum DistanceUnits {
        UNSPECIFIED("unspecified", 0), FEET("ft", GpxUtils.M2FT), MILES("mi",
            GpxUtils.M2MI), METERS("m", 1.0), KILOMETERS("km", .001);
        private final String name;
        private final double factor;

        DistanceUnits(String name, double factor) {
            this.name = name;
            this.factor = factor;
        }

        /**
         * Returns the name of the units.
         * 
         * @return
         */
        public String getName() {
            return name;
        }

        /**
         * Converts a distance in meters to these units.
         * 
         * @param distance
         * @return
         */
        public double convertMeters(double distance) {
            return factor * distance;
        }
    };

    public static enum VelocityUnits {
        UNSPECIFIED("unspecified", 0), FEETPERSEC("ft/sec", GpxUtils.M2FT), MILESPERHOUR(
            "mi/hr", GpxUtils.M2MI / GpxUtils.SEC2HR), METERSPERSEC("m/sec",
            1.0), KILOMETERSPERHR("km/hr", .001 / GpxUtils.SEC2HR);
        private final String name;
        private final double factor;

        VelocityUnits(String name, double factor) {
            this.name = name;
            this.factor = factor;
        }

        /**
         * Returns the name of the units.
         * 
         * @return
         */
        public String getName() {
            return name;
        }

        /**
         * Converts a velocity in meters/sec to these units.
         * 
         * @param velocity
         * @return
         */
        public double convertMetersPerSec(double velocity) {
            return factor * velocity;
        }
    };

}
