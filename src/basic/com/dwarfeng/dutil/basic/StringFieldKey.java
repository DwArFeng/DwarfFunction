package com.dwarfeng.dutil.basic;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.dutil.basic.str.Name;

/**
 * 关于这个工具包的所有异常文本字段的主键枚举。
 * <p>
 * 此枚举是对内使用的，它的主要作用是返回程序中所需要的一些字段（尤其是异常字段）。 <br>
 * 请不要在外部程序中调用此包的枚举，因为该包主要是对内使用，其本身不保证兼容性。
 * <p>
 * <b>注意：</b> 该类在设计的时候不考虑兼容性，当发生不向上兼容的改动时，作者没有义务在变更日志中说明。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public enum StringFieldKey implements Name {

	/** 欢迎文本字段 */
	WELCOME_STRING(new DefaultName("WelcomeString")),

	/** ClassUtil类第0号文本字段 */
	CLASSUTIL_0(new DefaultName("ClassUtil.0")),
	/** ClassUtil类第1号文本字段 */
	CLASSUTIL_1(new DefaultName("ClassUtil.1")),
	/** ClassUtil类第2号文本字段 */
	CLASSUTIL_2(new DefaultName("ClassUtil.2")),
	/** ClassUtil类第3号文本字段 */
	CLASSUTIL_3(new DefaultName("ClassUtil.3")),

	/** DuplicateIdException类第0号文本字段 */
	DUPLICATEIDEXCEPTION_0(new DefaultName("DuplicateIdException.0")),

	/** ToStringComparator类第0号文本字段 */
	TOSTRINGCOMPARATOR_0(new DefaultName("ToStringComparator.0")),

	/** JAdjustableBorderPanel类第0号文本字段 */
	JADJUSTABLEBORDERPANEL_0(new DefaultName("JAdjustableBorderPanel.0")),

	/** TagRunner类第0号文本字段 */
	TAGRUNNER_0(new DefaultName("TagRunner.0")),
	/** TagRunner类第1号文本字段 */
	TAGRUNNER_1(new DefaultName("TagRunner.1")),

	/** TimeMeasurer类第0号文本字段 */
	TIMEMEASURER_0(new DefaultName("TimeMeasurer.0")),
	/** TimeMeasurer类第1号文本字段 */
	TIMEMEASURER_1(new DefaultName("TimeMeasurer.1")),
	/** TimeMeasurer类第2号文本字段 */
	TIMEMEASURER_2(new DefaultName("TimeMeasurer.2")),
	/** TimeMeasurer类第3号文本字段 */
	TIMEMEASURER_3(new DefaultName("TimeMeasurer.3")),
	/** TimeMeasurer类第4号文本字段 */
	TIMEMEASURER_4(new DefaultName("TimeMeasurer.4")),
	/** TimeMeasurer类第5号文本字段 */
	TIMEMEASURER_5(new DefaultName("TimeMeasurer.5")),

	/** EventListenerWeakSet类第0号文本字段 */
	EVENTLISTENERWEAKSET_0(new DefaultName("EventListenerWeakSet.0")),
	/** MuaListModel类第0号文本字段 */
	MUALISTMODEL_0(new DefaultName("MuaListModel.0")),
	/** StringBuilderOutputStream类第0号文本字段 */
	STRINGOUTPUTSTREAM_0(new DefaultName("StringOutputStream.0")),
	/** StringBuilderInputStream类第0号文本字段 */
	STRINGINPUTSTREAM_0(new DefaultName("StringInputStream.0")),
	/** StringBuilderInputStream类第0号文本字段 */
	STRINGINPUTSTREAM_1(new DefaultName("StringInputStream.1")),

	/** ArrayUtil类第0号文本字段 */
	ARRAYUTIL_0(new DefaultName("ArrayUtil.0")),
	/** ArrayUtil类第1号文本字段 */
	ARRAYUTIL_1(new DefaultName("ArrayUtil.1")),
	/** ArrayUtil类第2号文本字段 */
	ARRAYUTIL_2(new DefaultName("ArrayUtil.2")),

	/** FothAlgebraUtill类第0号文本字段 */
	FOTHALGEBRAUTIL_0(new DefaultName("FothAlgebraUtil.0")),
	/** FothAlgebraUtill类第1号文本字段 */
	FOTHALGEBRAUTIL_1(new DefaultName("FothAlgebraUtil.1")),
	/** FothAlgebraUtill类第2号文本字段 */
	FOTHALGEBRAUTIL_2(new DefaultName("FothAlgebraUtil.2")),

	/** Algebra包RealNumber字段 */
	ALGEBRA_REALNUMBER(new DefaultName("Algebra_RealNumber")),
	/** Algebra包Variable字段 */
	ALGEBRA_VARIABLE(new DefaultName("Algebra_Variable")),
	/** Algebra包VariableSpace字段 */
	Algebra_VariableSpace(new DefaultName("Algebra_VariableSpace")),

	/** Linalge包RowVector字段 */
	Linalge_RowVector(new DefaultName("Linalge_RowVector")),
	/** Linalge包ColVector字段 */
	Linalge_ColVector(new DefaultName("Linalge_ColVector")),
	/** Linalge包Matrix字段 */
	Linalge_Matrix(new DefaultName("Linalge_Matrix")),

	/** CollectionUtil类第0号文本字段 */
	CollectionUtil_0(new DefaultName("CollectionUtil.0")),
	/** CollectionUtil类第1号文本字段 */
	CollectionUtil_1(new DefaultName("CollectionUtil.1")),
	/** CollectionUtil类第2号文本字段 */
	CollectionUtil_2(new DefaultName("CollectionUtil.2")),
	/** CollectionUtil类第3号文本字段 */
	CollectionUtil_3(new DefaultName("CollectionUtil.3")),
	/** CollectionUtil类第4号文本字段 */
	CollectionUtil_4(new DefaultName("CollectionUtil.4")),
	/** CollectionUtil类第5号文本字段 */
	CollectionUtil_5(new DefaultName("CollectionUtil.5")),
	/** CollectionUtil类第6号文本字段 */
	CollectionUtil_6(new DefaultName("CollectionUtil.6")),
	/** CollectionUtil类第7号文本字段 */
	CollectionUtil_7(new DefaultName("CollectionUtil.7")),
	/** CollectionUtil类第8号文本字段 */
	CollectionUtil_8(new DefaultName("CollectionUtil.8")),
	/** CollectionUtil类第9号文本字段 */
	CollectionUtil_9(new DefaultName("CollectionUtil.9")),
	/** CollectionUtil类第10号文本字段 */
	CollectionUtil_10(new DefaultName("CollectionUtil.10")),
	/** CollectionUtil类第11号文本字段 */
	CollectionUtil_11(new DefaultName("CollectionUtil.11")),
	/** CollectionUtil类第12号文本字段 */
	CollectionUtil_12(new DefaultName("CollectionUtil.12")),
	/** CollectionUtil类第13号文本字段 */
	CollectionUtil_13(new DefaultName("CollectionUtil.13")),
	/** CollectionUtil类第14号文本字段 */
	CollectionUtil_14(new DefaultName("CollectionUtil.14")),
	/** CollectionUtil类第15号文本字段 */
	CollectionUtil_15(new DefaultName("CollectionUtil.15")),
	/** CollectionUtil类第16号文本字段 */
	CollectionUtil_16(new DefaultName("CollectionUtil.16")),

	/** NameableComparator类第0号文本字段 */
	NameableComparator_0(new DefaultName("NameableComparator.0")),

	/** FothValueComparator类第0号文本字段 */
	FothValueComparator_0(new DefaultName("FothValueComparator.0")),

	/** QuickFothVariable类第0号文本字段 */
	QuickFothVariable_0(new DefaultName("QuickFothVariable.0")),
	/** QuickFothVariable类第1号文本字段 */
	QuickFothVariable_1(new DefaultName("QuickFothVariable.1")),

	/** FothVariable类第0号文本字段 */
	FothVariable_0(new DefaultName("FothVariable.0")),

	/** DefaultFormulaRowVector类第0号文本字段 */
	DefaultFormulaRowVector_0(new DefaultName("DefaultFormulaRowVector.0")),
	/** DefaultFormulaRowVector类第1号文本字段 */
	DefaultFormulaRowVector_1(new DefaultName("DefaultFormulaRowVector.1")),
	/** DefaultFormulaRowVector类第2号文本字段 */
	DefaultFormulaRowVector_2(new DefaultName("DefaultFormulaRowVector.2")),
	/** DefaultFormulaRowVector类第3号文本字段 */
	DefaultFormulaRowVector_3(new DefaultName("DefaultFormulaRowVector.3")),
	/** DefaultFormulaRowVector类第4号文本字段 */
	DefaultFormulaRowVector_4(new DefaultName("DefaultFormulaRowVector.4")),
	/** DefaultFormulaRowVector类第5号文本字段 */
	DefaultFormulaRowVector_5(new DefaultName("DefaultFormulaRowVector.5")),
	/** DefaultFormulaRowVector类第6号文本字段 */
	DefaultFormulaRowVector_6(new DefaultName("DefaultFormulaRowVector.6")),
	/** DefaultFormulaRowVector类第7号文本字段 */
	DefaultFormulaRowVector_7(new DefaultName("DefaultFormulaRowVector.7")),
	/** DefaultFormulaRowVector类第8号文本字段 */
	DefaultFormulaRowVector_8(new DefaultName("DefaultFormulaRowVector.8")),

	/** DefaultFormulaColumnVector类第0号文本字段 */
	DefaultFormulaColumnVector_0(new DefaultName("DefaultFormulaColumnVector.0")),
	/** DefaultFormulaColumnVector类第1号文本字段 */
	DefaultFormulaColumnVector_1(new DefaultName("DefaultFormulaColumnVector.1")),
	/** DefaultFormulaColumnVector类第2号文本字段 */
	DefaultFormulaColumnVector_2(new DefaultName("DefaultFormulaColumnVector.2")),
	/** DefaultFormulaColumnVector类第3号文本字段 */
	DefaultFormulaColumnVector_3(new DefaultName("DefaultFormulaColumnVector.3")),
	/** DefaultFormulaColumnVector类第4号文本字段 */
	DefaultFormulaColumnVector_4(new DefaultName("DefaultFormulaColumnVector.4")),
	/** DefaultFormulaColumnVector类第5号文本字段 */
	DefaultFormulaColumnVector_5(new DefaultName("DefaultFormulaColumnVector.5")),
	/** DefaultFormulaColumnVector类第6号文本字段 */
	DefaultFormulaColumnVector_6(new DefaultName("DefaultFormulaColumnVector.6")),

	/** LinalgeUtil类第0号文本字段 */
	LinalgeUtil_0(new DefaultName("LinalgeUtil.0")),
	/** LinalgeUtil类第1号文本字段 */
	LinalgeUtil_1(new DefaultName("LinalgeUtil.1")),
	/** LinalgeUtil类第2号文本字段 */
	LinalgeUtil_2(new DefaultName("LinalgeUtil.2")),
	/** LinalgeUtil类第3号文本字段 */
	LinalgeUtil_3(new DefaultName("LinalgeUtil.3")),
	/** LinalgeUtil类第4号文本字段 */
	LinalgeUtil_4(new DefaultName("LinalgeUtil.4")),

	/** FothValue类第0号文本字段 */
	FothValue_0(new DefaultName("FothValue.0")),

	/** DefaultFormulaMatrix类第0号文本字段 */
	DefaultFormulaMatrix_0(new DefaultName("DefaultFormulaMatrix.0")),
	/** DefaultFormulaMatrix类第1号文本字段 */
	DefaultFormulaMatrix_1(new DefaultName("DefaultFormulaMatrix.1")),
	/** DefaultFormulaMatrix类第2号文本字段 */
	DefaultFormulaMatrix_2(new DefaultName("DefaultFormulaMatrix.2")),
	/** DefaultFormulaMatrix类第3号文本字段 */
	DefaultFormulaMatrix_3(new DefaultName("DefaultFormulaMatrix.3")),
	/** DefaultFormulaMatrix类第4号文本字段 */
	DefaultFormulaMatrix_4(new DefaultName("DefaultFormulaMatrix.4")),
	/** DefaultFormulaMatrix类第5号文本字段 */
	DefaultFormulaMatrix_5(new DefaultName("DefaultFormulaMatrix.5")),
	/** DefaultFormulaMatrix类第6号文本字段 */
	DefaultFormulaMatrix_6(new DefaultName("DefaultFormulaMatrix.6")),

	/** DefaultColumnVector类第0号文本字段 */
	DefaultColumnVector_0(new DefaultName("DefaultColumnVector.0")),
	/* DefaultColumnVector类第1号文本字段 */
	DefaultColumnVector_1(new DefaultName("DefaultColumnVector.1")),
	/* DefaultColumnVector类第2号文本字段 */
	DefaultColumnVector_2(new DefaultName("DefaultColumnVector.2")),
	/* DefaultColumnVector类第3号文本字段 */
	DefaultColumnVector_3(new DefaultName("DefaultColumnVector.3")),
	/* DefaultColumnVector类第4号文本字段 */
	DefaultColumnVector_4(new DefaultName("DefaultColumnVector.4")),

	/** DefaultRowVector类第0号文本字段 */
	DefaultRowVector_0(new DefaultName("DefaultRowVector.0")),
	/** DefaultRowVector类第1号文本字段 */
	DefaultRowVector_1(new DefaultName("DefaultRowVector.1")),
	/** DefaultRowVector类第2号文本字段 */
	DefaultRowVector_2(new DefaultName("DefaultRowVector.2")),
	/** DefaultRowVector类第3号文本字段 */
	DefaultRowVector_3(new DefaultName("DefaultRowVector.3")),
	/** DefaultRowVector类第4号文本字段 */
	DefaultRowVector_4(new DefaultName("DefaultRowVector.4")),
	/** DefaultRowVector类第5号文本字段 */
	DefaultRowVector_5(new DefaultName("DefaultRowVector.5")),
	/** DefaultRowVector类第6号文本字段 */
	DefaultRowVector_6(new DefaultName("DefaultRowVector.6")),

	/** DefaultMatrix类第0号文本字段 */
	DefaultMatrix_0(new DefaultName("DefaultMatrix.0")),
	/** DefaultMatrix类第1号文本字段 */
	DefaultMatrix_1(new DefaultName("DefaultMatrix.1")),
	/** DefaultMatrix类第2号文本字段 */
	DefaultMatrix_2(new DefaultName("DefaultMatrix.2")),
	/** DefaultMatrix类第3号文本字段 */
	DefaultMatrix_3(new DefaultName("DefaultMatrix.3")),
	/** DefaultMatrix类第4号文本字段 */
	DefaultMatrix_4(new DefaultName("DefaultMatrix.4")),
	/** DefaultMatrix类第5号文本字段 */
	DefaultMatrix_5(new DefaultName("DefaultMatrix.5")),

	/** FormulaLinalgeUtil类第0号文本字段 */
	FormulaLinalgeUtil_0(new DefaultName("FormulaLinalgeUtil.0")),
	/** FormulaLinalgeUtil类第1号文本字段 */
	FormulaLinalgeUtil_1(new DefaultName("FormulaLinalgeUtil.1")),

	/** FothVariableSpace类第0号文本字段 */
	FothVariableSpace_0(new DefaultName("FothVariableSpace.0")),
	/** FothVariableSpace类第1号文本字段 */
	FothVariableSpace_1(new DefaultName("FothVariableSpace.1")),

	/** DefaultTag类第0号文本字段 */
	DefaultTag_0(new DefaultName("DefaultTag.0")),
	/** DefaultTag类第1号文本字段 */
	DefaultTag_1(new DefaultName("DefaultTag.1")),

	/** DefaultListItemModel类第0号文本字段 */
	DefaultListItemModel_0(new DefaultName("DefaultListItemModel.0")),
	/** DefaultListItemModel类第1号文本字段 */
	DefaultListItemModel_1(new DefaultName("DefaultListItemModel.1")),
	/** DefaultListItemModel类第2号文本字段 */
	DefaultListItemModel_2(new DefaultName("DefaultListItemModel.2")),
	/** DefaultListItemModel类第3号文本字段 */
	DefaultListItemModel_3(new DefaultName("DefaultListItemModel.3")),
	/** DefaultListItemModel类第4号文本字段 */
	DefaultListItemModel_4(new DefaultName("DefaultListItemModel.4")),
	/** DefaultListItemModel类第5号文本字段 */
	DefaultListItemModel_5(new DefaultName("DefaultListItemModel.5")),
	/** DefaultListItemModel类第6号文本字段 */
	DefaultListItemModel_6(new DefaultName("DefaultListItemModel.6")),

	/** JointIterator类第0号文本字段 */
	JointIterator_0(new DefaultName("JointIterator.0")),
	/** JointIterator类第1号文本字段 */
	JointIterator_1(new DefaultName("JointIterator.1")),
	/** JointIterator类第2号文本字段 */
	JointIterator_2(new DefaultName("JointIterator.2")),

	/** NumberUtil类第0号文本字段 */
	NumberUtil_0(new DefaultName("NumberUtil.0")),
	/** NumberUtil类第1号文本字段 */
	NumberUtil_1(new DefaultName("NumberUtil.1")),

	/** StreamConfigLoader类第0号文本字段 */
	StreamConfigLoader_0(new DefaultName("StreamConfigLoader.0")),

	/** StreamConfigSaver类第0号文本字段 */
	StreamConfigSaver_0(new DefaultName("StreamConfigSaver.0")),

	/** ConfigKey类第0号文本字段 */
	ConfigKey_0(new DefaultName("ConfigKey.0")),

	/** SwingUtil类第0号文本字段 */
	SwingUtil_0(new DefaultName("SwingUtil.0")),

	/** ConfigViewModel类第0号文本字段 */
	ConfigViewModel_0(new DefaultName("ConfigViewModel.0")),
	/** ConfigViewModel类第1号文本字段 */
	ConfigViewModel_1(new DefaultName("ConfigViewModel.1")),

	/** ConfigTablePanel类第0号文本字段 */
	ConfigTablePanel_0(new DefaultName("ConfigTablePanel.0")),
	/** ConfigTablePanel类第1号文本字段 */
	ConfigTablePanel_1(new DefaultName("ConfigTablePanel.1")),

	/** DefaultConfigModel类第0号文本字段 */
	DefaultConfigModel_0(new DefaultName("DefaultConfigModel.0")),
	/** DefaultConfigModel类第1号文本字段 */
	DefaultConfigModel_1(new DefaultName("DefaultConfigModel.1")),
	/** DefaultConfigModel类第2号文本字段 */
	DefaultConfigModel_2(new DefaultName("DefaultConfigModel.2")),
	/** DefaultConfigModel类第3号文本字段 */
	DefaultConfigModel_3(new DefaultName("DefaultConfigModel.3")),
	/** DefaultConfigModel类第4号文本字段 */
	DefaultConfigModel_4(new DefaultName("DefaultConfigModel.4")),
	/** DefaultConfigModel类第5号文本字段 */
	DefaultConfigModel_5(new DefaultName("DefaultConfigModel.5")),

	/** PropertiesConfigSaver类第0号文本字段 */
	PropertiesConfigSaver_0(new DefaultName("PropertiesConfigSaver.0")),
	/** PropertiesConfigSaver类第1号文本字段 */
	PropertiesConfigSaver_1(new DefaultName("PropertiesConfigSaver.1")),

	/** PropertiesConfigLoader类第0号文本字段 */
	PropertiesConfigLoader_0(new DefaultName("PropertiesConfigLoader.0")),
	/** PropertiesConfigLoader类第1号文本字段 */
	PropertiesConfigLoader_1(new DefaultName("PropertiesConfigLoader.1")),

	/** ConfigUtil类第0号文本字段 */
	ConfigUtil_0(new DefaultName("ConfigUtil.0")),

	/** DefaultConfigFirmProps类第0号文本字段 */
	DefaultConfigFirmProps_0(new DefaultName("DefaultConfigFirmProps.0")),
	/** DefaultConfigFirmProps类第1号文本字段 */
	DefaultConfigFirmProps_1(new DefaultName("DefaultConfigFirmProps.1")),
	/** DefaultConfigFirmProps类第2号文本字段 */
	DefaultConfigFirmProps_2(new DefaultName("DefaultConfigFirmProps.2")),

	/** FactoriesByString类第0号文本字段 */
	FactoriesByString_0(new DefaultName("FactoriesByString.0")),
	/** FactoriesByString类第1号文本字段 */
	FactoriesByString_1(new DefaultName("FactoriesByString.1")),

	/** NumberedThreadFactory类第0号文本字段 */
	NumberedThreadFactory_0(new DefaultName("NumberedThreadFactory.0")),

	/** JExconsole类第0号文本字段 */
	JEXCONSOLE_0(new DefaultName("JExconsole.0")),
	/** JExconsole类第1号文本字段 */
	JEXCONSOLE_1(new DefaultName("JExconsole.1")),
	/** JExconsole类第2号文本字段 */
	JEXCONSOLE_2(new DefaultName("JExconsole.2")),
	/** JExconsole类第3号文本字段 */
	JEXCONSOLE_3(new DefaultName("JExconsole.3")),
	/** JExconsole类第4号文本字段 */
	JEXCONSOLE_4(new DefaultName("JExconsole.4")),

	/** StreamLoader类第0号文本字段 */
	STREAMLOADER_0(new DefaultName("StreamLoader.0")),

	/** StreamSaver类第0号文本字段 */
	STREAMSAVER_0(new DefaultName("StreamSaver.0")),

	/** AbstractListModel类第0号文本字段 */
	ABSTRACTLISTMODEL_0(new DefaultName("AbstractListModel.0")),

	/** AbstractSetModel类第0号文本字段 */
	ABSTRACTSETMODEL_0(new DefaultName("AbstractSetModel.0")),

	/** AbstractMapModel类第0号文本字段 */
	ABSTRACTMAPMODEL_0(new DefaultName("AbstractMapModel.0")),

	/** AbstractKeyListModel类第0号文本字段 */
	ABSTRACTKEYLISTMODEL_0(new DefaultName("AbstractKeyListModel.0")),

	/** AbstractKeySetModel类第0号文本字段 */
	ABSTRACTKEYSETMODEL_0(new DefaultName("AbstractKeySetModel.0")),

	/** ModelUtil类第0号文本字段 */
	MODELUTIL_0(new DefaultName("ModelUtil.0")),
	/** ModelUtil类第1号文本字段 */
	MODELUTIL_1(new DefaultName("ModelUtil.1")),
	/** ModelUtil类第2号文本字段 */
	MODELUTIL_2(new DefaultName("ModelUtil.2")),
	/** ModelUtil类第3号文本字段 */
	MODELUTIL_3(new DefaultName("ModelUtil.3")),
	;

	private final Name name;

	private StringFieldKey(Name name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.str.Name#getName()
	 */
	@Override
	public String getName() {
		return name.getName();
	}
}