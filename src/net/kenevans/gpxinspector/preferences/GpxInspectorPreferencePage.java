package net.kenevans.gpxinspector.preferences;

import net.kenevans.gpxinspector.plugin.Activator;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
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
        gpxDirectoryFieldEditor = new DirectoryFieldEditor(IPreferenceConstants.P_GPX_DIR,
            "GPX file directory:", parent);
        addField(gpxDirectoryFieldEditor);
        String toolTip = "Default directory for your GPX files.";
        gpxDirectoryFieldEditor.getLabelControl(parent).setToolTipText(toolTip);
        gpxDirectoryFieldEditor.getTextControl(parent).setToolTipText(toolTip);
        
        notMovingFieldEditor = new StringFieldEditor(
            IPreferenceConstants.P_NO_MOVE_SPEED,
            "\"Not Moving\" Speed [m/sec]:", parent);
        toolTip = "Speeds below the \"Not Moving\" speed are considered to not be\n"
            + "moving when calculating track statistics such as the elapsed time\n"
            + "while moving and the average speed while moving.\n"
            + "Note that 1 mi/hr is 0.44704 m/sec.";
        notMovingFieldEditor.getLabelControl(parent).setToolTipText(toolTip);
        notMovingFieldEditor.getTextControl(parent).setToolTipText(toolTip);
        addField(notMovingFieldEditor);
        // KE: Consider using wrapped Text instead of Label for these comments.
        // Label label = new Label(parent, SWT.WRAP);
        // label
        // .setText("Speeds below the \"Not Moving\" speed are considered to not be\n"
        // +
        // "moving when calculating track statistics such as the elapsed time\n"
        // + "while moving and the average speed while moving.\n"
        // + "Note that 1 mi/hr is 0.44704 m/sec.");
        // GridDataFactory.fillDefaults().grab(false, false).span(3, 1)
        // .applyTo(label);
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