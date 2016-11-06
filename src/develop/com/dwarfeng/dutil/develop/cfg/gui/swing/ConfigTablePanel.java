package com.dwarfeng.dutil.develop.cfg.gui.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 使用表格的配置编辑面板。
 * @author  DwArFeng
 * @since 1.8
 */
public class ConfigTablePanel extends JPanel {
	
	/**配置表格*/
	protected final JTable table;
	
	

	/**
	 * Create the panel.
	 */
	public ConfigTablePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}
	
	
	
	
	

}
