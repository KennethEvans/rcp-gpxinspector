package net.kenevans.gpxinspector.preferences;

import net.kenevans.gpxinspector.plugin.Activator;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * GpxInspectorPreferencePage
 * 
 * @author Kenneth Evans, Jr.
 */
public class GpxInspectorPreferencePage extends FieldEditorPreferencePage
    implements IWorkbenchPreferencePage, IPreferenceConstants
{
    private DirectoryFieldEditor gpxDirectoryFieldEditor;
    private StringFieldEditor notMovingFieldEditor;

    public GpxInspectorPreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common
     * GUI blocks needed to manipulate various types of preferences. Each field
     * editor knows how to save and restore itself.
     */
    public void createFieldEditors() {
        Composite parent = getFieldEditorParent();
        gpxDirectoryFieldEditor = new DirectoryFieldEditor(
            IPreferenceConstants.P_GPX_DIR, "GPX file directory:", parent);
        addField(gpxDirectoryFieldEditor);
        String toolTip = "Default directory for your GPX files.";
        gpxDirectoryFieldEditor.getLabelControl(parent).setToolTipText(toolTip);
        gpxDirectoryFieldEditor.getTextControl(parent).setToolTipText(toolTip);

        Text text = new Text(parent, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
        text.setText("Track points with speeds below the \"Not Moving\" "
            + "speed are considered to be stationary when calculating track "
            + "statistics such as the  elapsed time while moving and the "
            + "average speed while moving. "
            + "Note that 1 mi/hr is 0.44704 m/sec.");
        GridDataFactory.fillDefaults().grab(false, false).span(3, 1)
            .hint(TEXT_WIDTH_MED, SWT.DEFAULT).applyTo(text);

        notMovingFieldEditor = new StringFieldEditor(
            IPreferenceConstants.P_NO_MOVE_SPEED,
            "\"Not Moving\" Speed [m/sec]:", parent);
        toolTip = "\"Not Moving\" speed in m/sec.";
        notMovingFieldEditor.getLabelControl(parent).setToolTipText(toolTip);
        notMovingFieldEditor.getTextControl(parent).setToolTipText(toolTip);
        addField(notMovingFieldEditor);
        text = new Text(parent, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
        text.setText("\nThe above preferences take place "
            + "immediately and do not require a restart.");
        GridDataFactory.fillDefaults().grab(false, false).span(3, 1)
            .hint(TEXT_WIDTH_MED, SWT.DEFAULT).applyTo(text);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        String stringVal = notMovingFieldEditor.getStringValue();
        try {
            Double.parseDouble(stringVal);
        } catch(NumberFormatException ex) {
            setErrorMessage("Invalid value for \"Not Moving\" speed!");
            return false;
        }
        return super.performOk();
    }

}