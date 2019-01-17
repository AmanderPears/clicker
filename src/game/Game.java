package game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagConstraints gridc = new GridBagConstraints();
	private int total = 0, buyAutoVal = 0;

	public Game() {
		setLayout(new GridBagLayout());

		//
		JLabel countDisplay = new JLabel();
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		add(countDisplay, gridc);

		JButton clickButton = new JButton("Click Me!");
		add(clickButton, gridc);

		JButton buyAuto = new JButton("Buy autoclicker ($100)");
		// buyAuto.setEnabled(false);
		gridc.gridwidth = 1;
		add(buyAuto, gridc);

		JLabel buyAutolbl = new JLabel("Auto clickers: 0");
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		add(buyAutolbl, gridc);

		//
		clickButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				total++;
				countDisplay.setText(Integer.toString(total));

			}
		});

		// bu
		Vector<Timer> timers = new Vector<Timer>();

		// update buybutton
		timers.addElement(new Timer());
		timers.lastElement().scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if ((total - 100) >= 0) {
					buyAuto.setEnabled(true);
				} else {
					buyAuto.setEnabled(false);
				}
			}
		}, 0, 100);

		/// add auto clickers
		buyAuto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((total - 100) >= 0) {
					buyAutoVal++;
					total -= 100;
					total++;

					timers.addElement(new Timer());
					timers.lastElement().scheduleAtFixedRate(new TimerTask() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							total++;
							buyAutolbl.setText("Auto clickers: " + buyAutoVal);
							countDisplay.setText(Integer.toString(total));
						}
					}, 0, 1000);
				}
				// add timer
				// remove button
				// remove(buyAuto);
				// repaint();

			}
		});
	}

}
