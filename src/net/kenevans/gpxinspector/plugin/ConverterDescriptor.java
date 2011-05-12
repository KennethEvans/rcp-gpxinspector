package net.kenevans.gpxinspector.plugin;

import java.io.File;

import net.kenevans.gpx.GpxType;
import net.kenevans.gpxinspector.converters.IGpxConverter;

import org.eclipse.core.runtime.IConfigurationElement;

/*
 * Created on May 11, 2011
 * By Kenneth Evans, Jr.
 */

/**
 * ConverterDescriptor is a proxy for a converter. It stores the information
 * from the plug-in registry, but will not load the defining plug-in until it is
 * necessary to load its required class.
 * 
 * @author Kenneth Evans, Jr.
 */
public class ConverterDescriptor implements IGpxConverter
{
    private IConfigurationElement ce;

    /**
     * ConverterDescriptor constructor.
     * 
     * @param ce The IConfigurationElement used to determine the parameters.
     */
    public ConverterDescriptor(IConfigurationElement ce) {
        this.ce = ce;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.kenevans.gpxinspector.converters.IGpxConverter#getFilterExtensions()
     */
    @Override
    public String getFilterExtensions() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.kenevans.gpxinspector.converters.IGpxConverter#isReadSupported(java
     * .io.File)
     */
    @Override
    public boolean isParseSupported(File file) {
        if(ce == null) {
            return false;
        }
        try {
            IGpxConverter converter = (IGpxConverter)ce
                .createExecutableExtension("class");
            return converter.isParseSupported(file);
        } catch(Throwable t) {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.kenevans.gpxinspector.converters.IGpxConverter#isWriteSupported(java
     * .io.File)
     */
    @Override
    public boolean isSaveSupported(File file) {
        if(ce == null) {
            return false;
        }
        try {
            IGpxConverter converter = (IGpxConverter)ce
                .createExecutableExtension("class");
            return converter.isSaveSupported(file);
        } catch(Throwable t) {
            return false;
        }
    }

    /**
     * Gets the id of the converter.
     * 
     * @return
     */
    public String getId() {
        if(ce == null) {
            return null;
        }
        return ce.getAttribute("id");
    }

    /**
     * Gets the name of the converter.
     * 
     * @return
     */
    public String getName() {
        if(ce == null) {
            return null;
        }
        return ce.getAttribute("name");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.kenevans.gpxinspector.converters.IGpxConverter#save(net.kenevans.
     * gpx.GpxType, java.io.File)
     */
    @Override
    public void save(String creator, GpxType gpxType, File file)
        throws Throwable {
        if(ce == null) {
            return;
        }
        IGpxConverter converter = (IGpxConverter)ce
            .createExecutableExtension("class");
        converter.save(creator, gpxType, file);
    }

    @Override
    public GpxType parse(File file) throws Throwable {
        if(ce == null) {
            return null;
        }
        IGpxConverter converter = (IGpxConverter)ce
            .createExecutableExtension("class");
        return converter.parse(file);
    }

}
