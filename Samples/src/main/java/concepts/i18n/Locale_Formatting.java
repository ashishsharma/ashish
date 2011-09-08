package main.java.concepts.i18n;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

// http://download.oracle.com/javase/tutorial/i18n/index.html

public class Locale_Formatting {
	
	// parameters - language, region
//	Locale locale = new Locale("en", "US");
	Locale locale = new Locale("fr", "FR");
//	Locale locale = new Locale("de", "DE");
	
	
	public static void main(String[] args) {
		Locale_Formatting l = new Locale_Formatting();
		l.formatLocaleDateTime();
	}
	
	public void localeText() {		
		
		// These resource properties files are loaded by classloader, similar to java classes. 
		// If a class is not in class path, then we import it. But properties file cannot be imported. 
		// So we need to specify the fully qualified name (like we do while importing a class - with a package path)
		String pckg = "main.java.concepts.i18n";
		String fileBundle = pckg + "." + "MessagesBundle";
		
		// tells the classloader to load a resource named MessagesBundle with package main.java.concepts.i18n"  
		
		// Resource bundles contain locale-specific objects. 
		// ResourceBundle is used to load locale specific property files
		// program can load properties from the resource bundle that is appropriate for the specified locale.
		ResourceBundle messages = ResourceBundle.getBundle(fileBundle, locale);
		
		// we configure key value pairs for different languages, and depending upon locale - ResourceBundle loads them.
		
		System.out.println(messages.getString("greetings"));
		System.out.println(messages.getString("inquiry"));
		System.out.println(messages.getString("farewell"));
	}
	
	public void formatLocaleDateTime() {
		Date today = new Date();
		
		//locale specific date
		DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		String val = df.format(today);
		System.out.println(val);
		
		//locale specific time
		DateFormat dt = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		val = dt.format(today);
		System.out.println(val);
		
		//locale specific date and time
		DateFormat ddt = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
		val = ddt.format(today);
		System.out.println(val);
		
		//locale specific date formatting in a custom format using SimpleDateFormat
		String pattern = "yyyy/MMM/dd, E, hh:mm:ss aaa z";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
		val = sdf.format(today);
		System.out.println(val);
		
	}
	
	public void formatLocaleNumberCurrency() {

		Double num = new Double(34598712.246);

		// locale specific number formatting
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		String val = nf.format(num.doubleValue());
		System.out.println(val); // en 34,598,712.246 | fr 34Ê598Ê712,246 | de 34.598.712,246

		// locale specific currency formatting
		NumberFormat nc = NumberFormat.getCurrencyInstance(locale);
		val = nc.format(num.doubleValue());
		System.out.println(val);
		
		// locale specific percent formatting
		NumberFormat np = NumberFormat.getPercentInstance(locale);
		val = np.format(0.75);
		System.out.println(val);
		
		// non-locale (general) decimal formatting - with custom formats
		String pattern = "###,###.##";
		DecimalFormat df = new DecimalFormat();
		df.applyPattern(pattern);
		val = df.format(num.doubleValue());
		System.out.println(val);
		
		// locale specific decimal formatting - with custom formats
		NumberFormat nmf = NumberFormat.getNumberInstance(locale);
		DecimalFormat dfmt = (DecimalFormat) nmf;
		dfmt.applyPattern(pattern);
		val = dfmt.format(num.doubleValue());
		System.out.println(val);

	}

}

// NOTES
// IN texts like -- 405,390 people have visited your website since January 1, 2009
// We cannot use resourceBundle approach discussed above. Because in different languages, verbs come at different places and there is no 1-1 mapping between word-word in different languages.
// In such cases - we use locale specific resource bundles with locale entries like -- template = Um {2,time,short} am {2,date,long} haben wir {1,number,integer} Raumschiffe auf dem Planeten Mars entdeckt.
// and we will replace the {} place holders with again locale specific formatting
// Java also provides MessageFormat - that can used for such instances. But that is outside the scope of this tutorial.
// http://download.oracle.com/javase/tutorial/i18n/format/messageFormat.html