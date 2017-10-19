package com.dwarfeng.dutil.basic;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.dutil.basic.str.Name;

/**
 * 关于这个工具包的所有异常文本字段的主键枚举。
 * <p>
 * 此枚举是对内使用的，它的主要作用是返回内部类所需要的一些字段（尤其是异常字段）。 <br>
 * 请不要在外部程序中调用此包的枚举，因为该枚举对内使用，其本身不保证兼容性。
 * <p>
 * <b>注意：</b> 该枚举在设计的时候不考虑兼容性，当发生不向上兼容的改动时，作者没有义务在变更日志中说明。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public enum ExceptionStringKey implements Name {

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
	/** ArrayUtil类第3号文本字段 */
	ARRAYUTIL_3(new DefaultName("ArrayUtil.3")),
	/** ArrayUtil类第4号文本字段 */
	ARRAYUTIL_4(new DefaultName("ArrayUtil.4")),

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
	COLLECTIONUTIL_0(new DefaultName("CollectionUtil.0")),
	/** CollectionUtil类第1号文本字段 */
	COLLECTIONUTIL_1(new DefaultName("CollectionUtil.1")),
	/** CollectionUtil类第2号文本字段 */
	COLLECTIONUTIL_2(new DefaultName("CollectionUtil.2")),
	/** CollectionUtil类第3号文本字段 */
	COLLECTIONUTIL_3(new DefaultName("CollectionUtil.3")),
	/** CollectionUtil类第4号文本字段 */
	COLLECTIONUTIL_4(new DefaultName("CollectionUtil.4")),
	/** CollectionUtil类第5号文本字段 */
	COLLECTIONUTIL_5(new DefaultName("CollectionUtil.5")),
	/** CollectionUtil类第6号文本字段 */
	COLLECTIONUTIL_6(new DefaultName("CollectionUtil.6")),
	/** CollectionUtil类第7号文本字段 */
	COLLECTIONUTIL_7(new DefaultName("CollectionUtil.7")),
	/** CollectionUtil类第8号文本字段 */
	COLLECTIONUTIL_8(new DefaultName("CollectionUtil.8")),
	/** CollectionUtil类第9号文本字段 */
	COLLECTIONUTIL_9(new DefaultName("CollectionUtil.9")),
	/** CollectionUtil类第10号文本字段 */
	COLLECTIONUTIL_10(new DefaultName("CollectionUtil.10")),
	/** CollectionUtil类第11号文本字段 */
	COLLECTIONUTIL_11(new DefaultName("CollectionUtil.11")),
	/** CollectionUtil类第12号文本字段 */
	COLLECTIONUTIL_12(new DefaultName("CollectionUtil.12")),
	/** CollectionUtil类第13号文本字段 */
	COLLECTIONUTIL_13(new DefaultName("CollectionUtil.13")),
	/** CollectionUtil类第14号文本字段 */
	COLLECTIONUTIL_14(new DefaultName("CollectionUtil.14")),
	/** CollectionUtil类第15号文本字段 */
	COLLECTIONUTIL_15(new DefaultName("CollectionUtil.15")),
	/** CollectionUtil类第16号文本字段 */
	COLLECTIONUTIL_16(new DefaultName("CollectionUtil.16")),
	/** CollectionUtil类第17号文本字段 */
	COLLECTIONUTIL_17(new DefaultName("CollectionUtil.17")),
	/** CollectionUtil类第18号文本字段 */
	COLLECTIONUTIL_18(new DefaultName("CollectionUtil.18")),
	/** CollectionUtil类第19号文本字段 */
	COLLECTIONUTIL_19(new DefaultName("CollectionUtil.19")),
	/** CollectionUtil类第20号文本字段 */
	COLLECTIONUTIL_20(new DefaultName("CollectionUtil.20")),
	/** CollectionUtil类第21号文本字段 */
	COLLECTIONUTIL_21(new DefaultName("CollectionUtil.21")),

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
	NUMBERUTIL_0(new DefaultName("NumberUtil.0")),
	/** NumberUtil类第1号文本字段 */
	NUMBERUTIL_1(new DefaultName("NumberUtil.1")),
	/** NumberUtil类第2号文本字段 */
	NUMBERUTIL_2(new DefaultName("NumberUtil.2")),

	/** StreamConfigLoader类第0号文本字段 */
	StreamConfigLoader_0(new DefaultName("StreamConfigLoader.0")),

	/** StreamConfigSaver类第0号文本字段 */
	StreamConfigSaver_0(new DefaultName("StreamConfigSaver.0")),

	/** ConfigKey类第0号文本字段 */
	ConfigKey_0(new DefaultName("ConfigKey.0")),

	/** SwingUtil类第0号文本字段 */
	SWINGUTIL_0(new DefaultName("SwingUtil.0")),
	/** SwingUtil类第1号文本字段 */
	SWINGUTIL_1(new DefaultName("SwingUtil.1")),

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
	CONFIGUTIL_0(new DefaultName("ConfigUtil.0")),
	/** ConfigUtil类第1号文本字段 */
	CONFIGUTIL_1(new DefaultName("ConfigUtil.1")),

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
	/** ModelUtil类第4号文本字段 */
	MODELUTIL_4(new DefaultName("ModelUtil.4")),
	/** ModelUtil类第5号文本字段 */
	MODELUTIL_5(new DefaultName("ModelUtil.5")),
	/** ModelUtil类第6号文本字段 */
	MODELUTIL_6(new DefaultName("ModelUtil.6")),
	/** ModelUtil类第7号文本字段 */
	MODELUTIL_7(new DefaultName("ModelUtil.7")),
	/** ModelUtil类第8号文本字段 */
	MODELUTIL_8(new DefaultName("ModelUtil.8")),

	/** AbstractExconfigModel类第0号文本字段 */
	ABSTRACTEXCONFIGMODEL_0(new DefaultName("AbstractExconfigModel.0")),

	/** DefaultExconfigModel类第0号文本字段 */
	DEFAULTEXCONFIGMODEL_0(new DefaultName("DefaultExconfigModel.0")),
	/** DefaultExconfigModel类第1号文本字段 */
	DEFAULTEXCONFIGMODEL_1(new DefaultName("DefaultExconfigModel.1")),
	/** DefaultExconfigModel类第2号文本字段 */
	DEFAULTEXCONFIGMODEL_2(new DefaultName("DefaultExconfigModel.2")),
	/** DefaultExconfigModel类第3号文本字段 */
	DEFAULTEXCONFIGMODEL_3(new DefaultName("DefaultExconfigModel.3")),

	/** DelegateListModel类第0号文本字段 */
	DELEGATELISTMODEL_0(new DefaultName("DelegateListModel.0")),
	/** DelegateListModel类第1号文本字段 */
	DELEGATELISTMODEL_1(new DefaultName("DelegateListModel.1")),

	/** DelegateSetModel类第0号文本字段 */
	DELEGATESETMODEL_0(new DefaultName("DelegateSetModel.0")),
	/** DelegateSetModel类第1号文本字段 */
	DELEGATESETMODEL_1(new DefaultName("DelegateSetModel.1")),

	/** DelegateMapModel类第0号文本字段 */
	DELEGATEMAPMODEL_0(new DefaultName("DelegateMapModel.0")),
	/** DelegateMapModel类第1号文本字段 */
	DELEGATEMAPMODEL_1(new DefaultName("DelegateMapModel.1")),
	/** DelegateMapModel类第2号文本字段 */
	DELEGATEMAPMODEL_2(new DefaultName("DelegateMapModel.2")),

	/** DelegateKeyListModel类第0号文本字段 */
	DELEGATEKEYLISTMODEL_0(new DefaultName("DelegateKeyListModel.0")),

	/** MapKeySetModel类第0号文本字段 */
	MAPKEYSETMODEL_0(new DefaultName("MapKeySetModel.0")),
	/** MapKeySetModel类第1号文本字段 */
	MAPKEYSETMODEL_1(new DefaultName("MapKeySetModel.1")),

	/** DelegateKeySetModel类第0号文本字段 */
	DELEGATEKEYSETMODEL_0(new DefaultName("DelegateKeySetModel.0")),

	/** AbstractBackground类第0号文本字段 */
	ABSTRACTBACKGROUND_0(new DefaultName("AbstractBackground.0")),

	/** ExecutorServiceBackground类第0号文本字段 */
	EXECUTORSERVICEBACKGROUND_0(new DefaultName("ExecutorServiceBackground.0")),
	/** ExecutorServiceBackground类第1号文本字段 */
	EXECUTORSERVICEBACKGROUND_1(new DefaultName("ExecutorServiceBackground.1")),
	/** ExecutorServiceBackground类第2号文本字段 */
	EXECUTORSERVICEBACKGROUND_2(new DefaultName("ExecutorServiceBackground.2")),

	/** BackgroundUtil类第0号文本字段 */
	BACKGROUNDUTIL_0(new DefaultName("BackgroundUtil.0")),
	/** BackgroundUtil类第1号文本字段 */
	BACKGROUNDUTIL_1(new DefaultName("BackgroundUtil.1")),
	/** BackgroundUtil类第2号文本字段 */
	BACKGROUNDUTIL_2(new DefaultName("BackgroundUtil.2")),
	/** BackgroundUtil类第3号文本字段 */
	BACKGROUNDUTIL_3(new DefaultName("BackgroundUtil.3")),

	/** ResourceUtil类第0号文本字段 */
	RESOURCEUTIL_0(new DefaultName("ResourceUtil.0")),
	/** ResourceUtil类第1号文本字段 */
	RESOURCEUTIL_1(new DefaultName("ResourceUtil.1")),
	/** ResourceUtil类第2号文本字段 */
	RESOURCEUTIL_2(new DefaultName("ResourceUtil.2")),

	/** DelegateResourceHandler类第0号文本字段 */
	DELEGATERESOURCEHANDLER_0(new DefaultName("DelegateResourceHandler.0")),

	/** AbstractResource类第0号文本字段 */
	ABSTRACTRESOURCE_0(new DefaultName("AbstractResource.0")),

	/** Url2FileResource类第0号文本字段 */
	URL2FILERESOURCE_0(new DefaultName("Url2FileResource.0")),
	/** Url2FileResource类第1号文本字段 */
	URL2FILERESOURCE_1(new DefaultName("Url2FileResource.1")),

	/** XmlJar2FileResourceLoader类第0号文本字段 */
	XMLJAR2FILERESOURCELOADER_0(new DefaultName("XmlJar2FileResourceLoader.0")),
	/** XmlJar2FileResourceLoader类第1号文本字段 */
	XMLJAR2FILERESOURCELOADER_1(new DefaultName("XmlJar2FileResourceLoader.1")),
	/** XmlJar2FileResourceLoader类第2号文本字段 */
	XMLJAR2FILERESOURCELOADER_2(new DefaultName("XmlJar2FileResourceLoader.2")),
	/** XmlJar3FileResourceLoader类第3号文本字段 */
	XMLJAR2FILERESOURCELOADER_3(new DefaultName("XmlJar2FileResourceLoader.3")),
	/** XmlJar4FileResourceLoader类第4号文本字段 */
	XMLJAR2FILERESOURCELOADER_4(new DefaultName("XmlJar2FileResourceLoader.4")),

	/** AbstractI18nInfo类第0号文本字段 */
	ABSTRACTI18NINFO_0(new DefaultName("AbstractI18nInfo.0")),
	/** AbstractI18nInfo类第1号文本字段 */
	ABSTRACTI18NINFO_1(new DefaultName("AbstractI18nInfo.1")),

	/** I18nUtil类第0号文本字段 */
	I18NUTIL_0(new DefaultName("I18nUtil.0")),
	/** I18nUtil类第1号文本字段 */
	I18NUTIL_1(new DefaultName("I18nUtil.1")),
	/** I28nUtil类第2号文本字段 */
	I18NUTIL_2(new DefaultName("I28nUtil.2")),

	/** DelegateI18nHandler类第0号文本字段 */
	DELEGATEI18NHANDLER_0(new DefaultName("DelegateI18nHandler.0")),

	/** PropUrlI18nInfo类第0号文本字段 */
	PROPURLI18NINFO_0(new DefaultName("PropUrlI18nInfo.0")),

	/** PropertiesI18n类第0号文本字段 */
	PROPERTIESI18N_0(new DefaultName("PropertiesI18n.0")),

	/** XmlPropFileI18nLoader类第0号文本字段 */
	XMLPROPFILEI18NLOADER_0(new DefaultName("XmlPropFileI18nLoader.0")),
	/** XmlPropFileI18nLoader类第1号文本字段 */
	XMLPROPFILEI18NLOADER_1(new DefaultName("XmlPropFileI18nLoader.1")),
	/** XmlPropFileI18nLoader类第2号文本字段 */
	XMLPROPFILEI18NLOADER_2(new DefaultName("XmlPropFileI18nLoader.2")),
	/** XmlPropFileI18nLoader类第3号文本字段 */
	XMLPROPFILEI18NLOADER_3(new DefaultName("XmlPropFileI18nLoader.3")),

	/** ThreadSafeUtil类第0号文本字段 */
	THREADSAFEUTIL_0(new DefaultName("ThreadSafeUtil.0")),

	/** FileUtil类第0号文本字段 */
	FILEUTIL_0(new DefaultName("FileUtil.0")),

	/** Interval类第0号文本字段 */
	INTERVAL_0(new DefaultName("Interval.0")),
	/** Interval类第1号文本字段 */
	INTERVAL_1(new DefaultName("Interval.1")),
	/** Interval类第2号文本字段 */
	INTERVAL_2(new DefaultName("Interval.2")),
	/** Interval类第3号文本字段 */
	INTERVAL_3(new DefaultName("Interval.3")),
	/** Interval类第4号文本字段 */
	INTERVAL_4(new DefaultName("Interval.4")),
	/** Interval类第5号文本字段 */
	INTERVAL_5(new DefaultName("Interval.5")),
	/** Interval类第6号文本字段 */
	INTERVAL_6(new DefaultName("Interval.6")),

	/** ThreadUtil类第0号文本字段 */
	THREADUTIL_0(new DefaultName("ThreadUtil.0")),

	/** AbstractReferenceModel类第0号文本字段 */
	ABSTRACTREFERENCEMODEL_0(new DefaultName("AbstractReferenceModel.0")),

	/** ImageUtil类第0号文本字段 */
	IMAGEUTIL_0(new DefaultName("ImageUtil.0")),
	/** ImageUtil类第1号文本字段 */
	IMAGEUTIL_1(new DefaultName("ImageUtil.1")),
	/** ImageUtil类第2号文本字段 */
	IMAGEUTIL_2(new DefaultName("ImageUtil.2")),
	/** ImageUtil类第3号文本字段 */
	IMAGEUTIL_3(new DefaultName("ImageUtil.3")),
	/** ImageUtil类第4号文本字段 */
	IMAGEUTIL_4(new DefaultName("ImageUtil.4")),
	
	/** ByteBufferInputStream类第0号文本字段 */
	BYTEBUFFERINPUTSTREAM_0(new DefaultName("ByteBufferInputStream.0")),
	
	/** ByteBufferOutputStream类第0号文本字段 */
	BYTEBUFFEROUTPUTSTREAM_0(new DefaultName("ByteBufferOutputStream.0")),
	
	;

	private final Name name;

	private ExceptionStringKey(Name name) {
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
