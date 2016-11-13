package com.dwarfeng.dutil.develop.cfg;

import java.util.Locale;

import com.dwarfeng.dutil.detool.gui.swing.JComponentTester;
import com.dwarfeng.dutil.develop.cfg.checker.BooleanChecker;
import com.dwarfeng.dutil.develop.cfg.checker.PositiveIntegerChecker;
import com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel;
import com.dwarfeng.dutil.develop.cfg.gui.swing.ConfigTablePanel;

final class Foo {

	public enum Cfg implements ConfigElements{
		CFG_0("Config.0", "FALSE", new BooleanChecker()),
		CFG_1("Config.1", "12450", new PositiveIntegerChecker()),
		
		;
		
		private final String name;
		private final ConfigValueChecker checker;
		private final String value;
		
		private Cfg(String name, String value, ConfigValueChecker checker) {
			this.name = name;
			this.checker = checker;
			this.value = value;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigElements#getConfigKey()
		 */
		@Override
		public ConfigKey getConfigKey() {
			return new ConfigKey(name);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigElements#getDefaultValue()
		 */
		@Override
		public String getDefaultValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigElements#getConfigValueChecker()
		 */
		@Override
		public ConfigValueChecker getConfigValueChecker() {
			return checker;
		}
		
	}
	
	public Foo() {
		// TODO Auto-generated constructor stub
	}
	
	public void test(){
		ConfigPort cp = ConfigUtil.newConfigPort(Cfg.values());
		
		ConfigGuiModel model = ConfigUtil.newConfigGuiModel(cp);
		
		ConfigTablePanel panel = new ConfigTablePanel();
		new JComponentTester(panel).setVisible(true);
		panel.setLocale(Locale.ENGLISH);
		panel.setModel(model);
	}

	public static void main(String[] args) {
		new Foo().test();
	}

}
