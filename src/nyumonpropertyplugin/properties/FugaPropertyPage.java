package nyumonpropertyplugin.properties;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

public class FugaPropertyPage extends PropertyPage {
	
	private static final String CONT_TITLE = "内容だよ：";
	private static final int TEXT_WIDTH = 60;

	/*
	private static final String PATH_TITLE = "Path:";
	private static final String OWNER_TITLE = "&Owner:";
	private static final String OWNER_PROPERTY = "OWNER";
	private static final String DEFAULT_OWNER = "John Doe";

	private static final int TEXT_FIELD_WIDTH = 50;

	private Text ownerText;
	*/

	/**
	 * Constructor for SamplePropertyPage.
	 */
	public FugaPropertyPage() {
		super();
	}

	private void addFirstSection(Composite parent) {
		Composite composite = createDefaultComposite(parent); // これは残す
		// ラベル
		Label contLabel = new Label(composite, SWT.NONE);
		contLabel.setText(CONT_TITLE);
		
		// テキスト表示（リードオンリー）
		// WRAP … 適当に折り返す
		// MULTI … 複数行を表示できる
		// READ_ONLY … 読み取り専用である
		Text valueText = new Text(composite,  SWT.WRAP | SWT.MULTI | SWT.READ_ONLY);
		GridData gd = new GridData();
		gd.widthHint = convertWidthInCharsToPixels(TEXT_WIDTH);
		valueText.setLayoutData(gd);
		
		// ファイル情報
		String filename = ((IResource)getElement()).getLocation().toString();
		valueText.setText(readFile(filename));
		
		/*
		//Label for path field
		Label pathLabel = new Label(composite, SWT.NONE);
		pathLabel.setText(PATH_TITLE);

		// Path text field
		Text pathValueText = new Text(composite, SWT.WRAP | SWT.READ_ONLY);
		pathValueText.setText(((IResource) getElement()).getFullPath().toString());
		*/
	}
	
	private String readFile(String filename){
		String content = new String();
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while(br.ready()){
				content += br.readLine() + "\n";
				int count = content.length();
				if(count > 50){ // 50文字だけ抽出
					content = content.substring(0, 49) + "...";
					break;
				}
			}
		}
		catch(FileNotFoundException ex){
			content = "ファイルがみつからないのです";
		}
		catch(IOException ex){
			content = "IOエラーっぽい";
		}
		return content;
	}

	/*
	private void addSeparator(Composite parent) {
		Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		separator.setLayoutData(gridData);
	}
	*/

	/*
	private void addSecondSection(Composite parent) {
		Composite composite = createDefaultComposite(parent);

		// Label for owner field
		Label ownerLabel = new Label(composite, SWT.NONE);
		ownerLabel.setText(OWNER_TITLE);

		// Owner text field
		ownerText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		GridData gd = new GridData();
		gd.widthHint = convertWidthInCharsToPixels(TEXT_FIELD_WIDTH);
		ownerText.setLayoutData(gd);

		// Populate owner text field
		try {
			String owner =
				((IResource) getElement()).getPersistentProperty(
					new QualifiedName("", OWNER_PROPERTY));
			ownerText.setText((owner != null) ? owner : DEFAULT_OWNER);
		} catch (CoreException e) {
			ownerText.setText(DEFAULT_OWNER);
		}
	}
	*/

	/**
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		addFirstSection(composite);
		// addSeparator(composite);
		// addSecondSection(composite);
		return composite;
	}

	private Composite createDefaultComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(data);

		return composite;
	}

	protected void performDefaults() {
		super.performDefaults(); // ### 本のサンプルだとこれも消してたけど、良いのかな？怖いから残そっと。
		// Populate the owner text field with the default value
		// ownerText.setText(DEFAULT_OWNER);
	}
	
	public boolean performOk() {
		/*
		// store the value in the owner text field
		try {
			((IResource) getElement()).setPersistentProperty(
				new QualifiedName("", OWNER_PROPERTY),
				ownerText.getText());
		} catch (CoreException e) {
			return false;
		}
		*/
		return true;
	}

}