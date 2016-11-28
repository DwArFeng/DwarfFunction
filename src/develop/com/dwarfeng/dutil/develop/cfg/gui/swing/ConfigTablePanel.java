package com.dwarfeng.dutil.develop.cfg.gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Locale;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.LabelFieldKey;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel;
import com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModelObverser;

/**
 * 使用表格的配置编辑面板。
 * @author  DwArFeng
 * @since 1.8
 */
public class ConfigTablePanel extends JPanel {
	
	/**配置表格*/
	protected final JTable table = new JTable();
	
	/**配置模型*/
	protected ConfigGuiModel model;
	/**错误的配置字段的颜色*/
	protected Color invalidConfigColor;
	
	
	private final InnerTableModel tableModel = new InnerTableModel();
	
	private final ConfigGuiModelObverser obverser = new ConfigGuiModelObverser() {
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModelObverser#fireEntryAdded(int, com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel.Entry)
		 */
		@Override
		public void fireValueAdded(int index, ConfigKey configKey, ConfigChecker configChecker, String defaultValue, String currentValue) {
			tableModel.fireTableRowsInserted(index, index);
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModelObverser#fireEntryRemoved(int)
		 */
		@Override
		public void fireValueRemoved(int index) {
			tableModel.fireTableRowsDeleted(index, index);
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModelObverser#fireEntryCleared()
		 */
		@Override
		public void fireValueCleared(int size) {
			tableModel.fireTableRowsDeleted(0, size - 1);
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModelObverser#fireEntryChanged(int, com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel.Entry)
		 */
		@Override
		public void fireValueChanged(int index, ConfigKey configKey, ConfigChecker configChecker, String defaultValue, String currentValue) {
			tableModel.fireTableCellUpdated(index, 0);
			tableModel.fireTableCellUpdated(index, 1);
			tableModel.fireTableCellUpdated(index, 2);
		}
		
	};

	private final TableCellRenderer tableHeaderRenderer = new DefaultTableCellRenderer(){
		
		private static final long serialVersionUID = -1854719520097517923L;

		/*
		 * (non-Javadoc)
		 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if(value instanceof LabelFieldKey){
				setText(DwarfUtil.getLabelField((LabelFieldKey)value, ConfigTablePanel.this.getLocale()));
			}
			return this;
		};
	};
	
	private final TableCellRenderer tableRenderer = new DefaultTableCellRenderer(){
		
	};
	
	

	/**
	 * Create the panel.
	 */
	public ConfigTablePanel() {
		init();
	}
	
	
	private void init(){
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(0).setHeaderValue(LabelFieldKey.ConfigTablePanel_0);
		table.getColumnModel().getColumn(1).setHeaderValue(LabelFieldKey.ConfigTablePanel_1);
		table.getColumnModel().getColumn(2).setHeaderValue(LabelFieldKey.ConfigTablePanel_2);
		table.getColumnModel().getColumn(0).setHeaderRenderer(tableHeaderRenderer);
		table.getColumnModel().getColumn(1).setHeaderRenderer(tableHeaderRenderer);
		table.getColumnModel().getColumn(2).setHeaderRenderer(tableHeaderRenderer);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
	}
	
	
	/**
	 * 获取该编辑面板的模型。
	 * <p> 注意该值有可能为 <code>null</code>。
	 * @return 该编辑界面的模型。
	 */
	public ConfigGuiModel getModel() {
		return model;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.Component#setLocale(java.util.Locale)
	 */
	@Override
	public void setLocale(Locale l) {
		super.setLocale(l);
		table.repaint();
		table.getTableHeader().repaint();
	}
	
	/**
	 * 设置该编辑面板的模型。
	 * @param model 指定的模型。
	 */
	public void setModel(ConfigGuiModel model) {
		if(Objects.nonNull(this.model)){
			this.model.removeObverser(obverser);
		}
		this.model = model;
		if(Objects.nonNull(this.model)){
			this.model.addObverser(obverser);
		}
		table.repaint();
	}



	private final class InnerTableModel extends AbstractTableModel{
		
		private static final long serialVersionUID = -4012998878556504575L;

		/*
		 * (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if(columnIndex < 0 || columnIndex > 2)
				throw new IndexOutOfBoundsException(String.format(DwarfUtil.getStringField(StringFieldKey.ConfigTablePanel_1), columnIndex));
			return ConfigKey.class;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		@Override
		public int getRowCount() {
			if(Objects.isNull(getModel())) return 0;
			return getModel().size();
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		@Override
		public int getColumnCount() {
			return 3;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(Objects.isNull(getModel()))
				throw new IndexOutOfBoundsException(String.format(DwarfUtil.getStringField(StringFieldKey.ConfigTablePanel_0), rowIndex));
			if(rowIndex < 0 || rowIndex > getModel().size() - 1)
				throw new IndexOutOfBoundsException(String.format(DwarfUtil.getStringField(StringFieldKey.ConfigTablePanel_0), rowIndex));
			if(columnIndex < 0 || columnIndex > 2)
				throw new IndexOutOfBoundsException(String.format(DwarfUtil.getStringField(StringFieldKey.ConfigTablePanel_1), columnIndex));
			
			return model.getConfigKey(rowIndex);
		}
		
	}

}
