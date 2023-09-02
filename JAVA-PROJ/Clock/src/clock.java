import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class clock extends JFrame  {

	//Calendar calendar;
	SimpleDateFormat timeformat;
	JLabel timelabel;
	String time ;

	// ------- day format ----------//
	SimpleDateFormat dayformat;
	JLabel daylabel;
	String day ;
	// ------- date format ----------//
	SimpleDateFormat dateformat;
	JLabel datelabel;
	String date ;

	clock() throws InterruptedException {
		//-----------------------------------------------------------------------------------//
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Clock");
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setSize(300,200);

		//---------------------------------- initialise time format ---------------------------------//

		timeformat = new SimpleDateFormat("hh:mm:ss a");

		//----------------------------------- time label ----------------------------------------------//

		timelabel = new JLabel();
		timelabel.setOpaque(true);
		timelabel.setBackground(Color.BLACK);
		timelabel.setForeground(Color.RED);
		timelabel.setFont(new Font(("Verdana"),Font.PLAIN,40));


		//---------------------------------- Day format ------------------------------------------//

		dayformat = new SimpleDateFormat("EEEE"); // E : day

		daylabel=new JLabel();
		daylabel.setFont(new Font(("Ink Free"),Font.PLAIN,35));

		//---------------------------------- Date format ------------------------------------------//

		dateformat = new SimpleDateFormat("MMMMM dd, yyyy"); // date

		datelabel=new JLabel();
		datelabel.setFont(new Font(("Arial"),Font.PLAIN,25));



		this.add(timelabel);
		this.add(daylabel);
		this.add(datelabel);
		this.setVisible(true);

		settime();

	}

	private void settime() throws InterruptedException {

		//-------------------------------- initialise time ------------------------------------------//

		while (true) {

			//time format
			time = timeformat.format(Calendar.getInstance().getTime());
			timelabel.setText(time);

			//day format
			day = dayformat.format(Calendar.getInstance().getTime());
			daylabel.setText(day);

			//date format
			date = dateformat.format(Calendar.getInstance().getTime());
			datelabel.setText(date);


			Thread.sleep(1000); // every 1 second
		}

	}
}
