package com.cisco.mazut.dts.model

import java.util.Date
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import com.cisco.mazut.dts.utils.{BigDecimalAdapter, RawDataAdapter}

/**
  * Created by dbort on 18.03.2016.
  */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "logs") class Logs {
  @XmlElement(name = "log", required = false) val logs: java.util.List[Log] = null
}

class Log {
  @XmlAttribute(name = "uid", required = false) val uid: String = ""
  @XmlElement(name = "nameWell", required = false) val nameWell: String = ""
  @XmlElement(name = "nameWellbore", required = false) val nameWellbore: String = ""
  @XmlElement(name = "name", required = false) val name: String = ""
  @XmlElement(name = "indexType", required = false) val indexType: String = ""
  @XmlElement(name = "startIndex", required = false) val startIndex: UomDoubleValue = null
  @XmlElement(name = "endIndex", required = false) val endIndex: UomDoubleValue = null
  @XmlElement(name = "stepIncrement", required = false) val stepIncrement: UomDoubleValue = null
  //@XmlElement(name = "startDateTimeIndex", required = false) @XmlJavaTypeAdapter(classOf[DateAdapter]) val startDateTimeIndex: Date = null
  @XmlElement(name = "startDateTimeIndex", required = false) val startDateTimeIndex: Date = null
  //@XmlElement(name = "startDateTimeIndex", required = false) val startDateTimeIndex: String = ""
  //@XmlElement(name = "endDateTimeIndex", required = false) @XmlJavaTypeAdapter(classOf[DateAdapter]) val endDateTimeIndex: Date = null
  @XmlElement(name = "endDateTimeIndex", required = false) val endDateTimeIndex: Date = null
  //@XmlElement(name = "endDateTimeIndex", required = false) val endDateTimeIndex: String = ""
  @XmlElement(name = "direction", required = false) val direction: String = ""
  @XmlElement(name = "indexCurve", required = false) val indexCurve: String = ""
  @XmlElement(name = "logCurveInfo", required = false) val logCurveInfo: java.util.List[LogCurveInfo] = null
  @XmlElement(name = "logData", required = false) val logData: LogData = null
  @XmlElement(name = "customData", required = false) val customData: CustomData = null
}

class UomDoubleValue {
  @XmlAttribute(name = "uom", required = false) val uom: String = ""
  @XmlValue val value: Double = 0.0
}

class UomIntValue {
  @XmlAttribute(name = "uom", required = false) val uom: String = ""
  @XmlValue val value: Int = 0
}

class LogCurveInfo {
  @XmlAttribute(name = "uid", required = false) val uid: String = ""
  @XmlElement(name = "mnemonic", required = false) val mnemonic: String = ""
  @XmlElement(name = "classWitsml", required = false) val classWitsml: String = ""
  @XmlElement(name = "unit", required = false) val unit: String = ""
  @XmlElement(name = "curveDescription", required = false) val curveDescription: String = ""
  @XmlElement(name = "typeLogData", required = false) val typeLogData: String = ""
}

class LogData {
  @XmlElement(name = "mnemonicList", required = false) val mnemonicList: String = ""
  @XmlElement(name = "unitList", required = false) val unitList: String = ""
  @XmlElement(name = "data", required = false) @XmlJavaTypeAdapter(classOf[RawDataAdapter])  val data: java.util.List[String] = null
}

class Data {
  @XmlElement(name = "unitList", required = false) val unitList: String = ""
}

class CustomData {
  @XmlElement(name = "acquisitionTime", required = false) val acquisitionTime: Double = 0.0
  @XmlElement(name = "referenceTemperature", required = false) val referenceTemperature: UomDoubleValue = null
  @XmlElement(name = "probe1Temperature", required = false) val probe1Temperature: UomIntValue = null
  @XmlElement(name = "probe2Temperature", required = false) val probe2Temperature: UomIntValue = null
  @XmlElement(name = "forwardMeasurementChannel", required = false) val forwardMeasurementChannel: Int = 0
  @XmlElement(name = "forwardSignalAverages", required = false) val forwardSignalAverages: Int = 0
  @XmlElement(name = "referenceProbeVoltage", required = false) val referenceProbeVoltage: Double = 0.0
  @XmlElement(name = "probe1Voltage", required = false) val probe1Voltage: Double = 0.0
  @XmlElement(name = "probe2Voltage", required = false) val probe2Voltage: Double = 0.0
  @XmlElement(name = "fibreStatusOk", required = false) val fibreStatusOk: Int = 0
  @XmlElement(name = "fibreBreakLocation", required = false) val fibreBreakLocation: String = ""
  @XmlElement(name = "isDoubleEnded", required = false) val isDoubleEnded: Int = 0
  @XmlElement(name = "reverseMeasurementChannel", required = false) val reverseMeasurementChannel: Int = 0
  @XmlElement(name = "reverseSignalAverages", required = false) val reverseSignalAverages: Int = 0
  @XmlElement(name = "measurementStatus", required = false) val measurementStatus: String = ""
  @XmlElement(name = "SystemSettings", required = false) val systemSettings: SystemSettings = null
  @XmlElement(name = "UserConfiguration", required = false) val userConfiguration: UserConfiguration = null
}

class SystemSettings {
  @XmlAttribute(name = "softwareVersion", required = false) val softwareVersion: String = ""
  @XmlElement(name = "DAQSettings", required = false) val daqSettings: DAQSettings = null
  @XmlElement(name = "HardwareSettings", required = false) val hardwareSettings: HardwareSettings = null
  @XmlElement(name = "LaserSettings", required = false) val laserSettings: LaserSettings = null
  @XmlElement(name = "SamplingIntervalSettings", required = false) val samplingIntervalSettings: SamplingIntervalSettings = null
  @XmlElement(name = "VoltageSweepSettings", required = false) val voltageSweepSettings: VoltageSweepSettings = null
  @XmlElement(name = "ProgramControlSettings", required = false) val programControlSettings: ProgramControlSettings = null
  @XmlElement(name = "ChannelSettings", required = false) val channelSettings: java.util.List[ChannelSettings] = null
  @XmlElement(name = "TemperatureReferenceSettings", required = false) val temperatureReferenceSettings: TemperatureReferenceSettings = null
  @XmlElement(name = "RawProcessingSettings", required = false) val rawProcessingSettings: RawProcessingSettings = null
  @XmlElement(name = "MeasurementSettings", required = false) val measurementSettings: MeasurementSettings = null
  @XmlElement(name = "OvershootCorrectionSettings", required = false) val overshootCorrectionSettings: OvershootCorrectionSettings = null
  @XmlElement(name = "CurveCalibrationSettings", required = false) val curveCalibrationSettings: CurveCalibrationSettings = null
  @XmlElement(name = "OperatingLimitsSettings", required = false) val operatingLimitsSettings: OperatingLimitsSettings = null
  @XmlElement(name = "SAHSettings", required = false) val sahSettings: SAHSettings = null
  @XmlElement(name = "RangeSettings", required = false) val rangeSettings: RangeSettings = null
  @XmlElement(name = "PowerTimingSettings", required = false) val powerTimingSettings: PowerTimingSettings = null
}

class DAQSettings {
  @XmlElement(name = "Card", required = false) val card: Int = 0
  @XmlElement(name = "MinimumRecordLength", required = false) val minimumRecordLength: Int = 0
  @XmlElement(name = "MaximumRecordLength", required = false) val maximumRecordLength: Int = 0
  @XmlElement(name = "PreTriggerSamples", required = false) val preTriggerSamples: Int = 0
  @XmlElement(name = "TriggerInDirection", required = false) val triggerInDirection: Int = 0
  @XmlElement(name = "TriggerMode", required = false) val triggerMode: Int = 0
  @XmlElement(name = "TriggerRateDividerFactor", required = false) val triggerRateDividerFactor: Int = 0
  @XmlElement(name = "ReferenceClockDirection", required = false) val referenceClockDirection: Int = 0
  @XmlElement(name = "ClockSource", required = false) val clockSource: Int = 0
}

class HardwareSettings {
  @XmlElement(name = "UltimaSerialNumber", required = false) val ultimaSerialNumber: String = ""
  @XmlElement(name = "DigitalLine", required = false) val digitalLine: java.util.List[DigitalLine] = null
  @XmlElement(name = "NumberOfChannels", required = false) val numberOfChannels: Int = 0
}

class DigitalLine {
  @XmlElement(name = "Name", required = false) val name: String = ""
  @XmlElement(name = "DataArray", required = false) val dataArray: String = ""
}

class LaserSettings {
  @XmlElement(name = "LaserIsControlled", required = false) val laserIsControlled: Int = 0
  @XmlElement(name = "LaserWarmupTime", required = false) val laserWarmupTime: Double = 0.0
  @XmlElement(name = "LaserCoolDownTime", required = false) val laserCoolDownTime: Double = 0.0
  @XmlElement(name = "DigitalLine", required = false) val digitalLine: java.util.List[DigitalLine] = null
  @XmlElement(name = "MinimumPulseWidth", required = false) val minimumPulseWidth: Double = 0.0
  @XmlElement(name = "MaximumPulseWidth", required = false) val maximumPulseWidth: Double = 0.0
  @XmlElement(name = "MinimumLaserPower", required = false) val minimumLaserPower: Double = 0.0
  @XmlElement(name = "MaximumLaserPower", required = false) val maximumLaserPower: Double = 0.0
  @XmlElement(name = "PulseWidth", required = false) val pulseWidth: Double = 0.0
  @XmlElement(name = "LaserPower", required = false) val laserPower: Double = 0.0
}

class SamplingIntervalSettings {
  @XmlElement(name = "SamplingInterval", required = false) val samplingInterval: Int = 0
  @XmlElement(name = "IsPermitted", required = false) val isPermitted: Int = 0
  @XmlElement(name = "PreTriggerShift", required = false) val preTriggerShift: Int = 0
  @XmlElement(name = "SignalOffsetRange", required = false) val signalOffsetRange: SignalOffsetRange = null
}

class SignalOffsetRange {
  @XmlElement(name = "SignalOffsetStart", required = false) val signalOffsetStart: Int = 0
  @XmlElement(name = "SignalOffsetStop", required = false) val signalOffsetStop: Int = 0
}

class VoltageSweepSettings {
  @XmlElement(name = "DigitalLine", required = false) val digitalLine: java.util.List[DigitalLine] = null
  @XmlElement(name = "Amplitude", required = false) val amplitude: Double = 0.0
  @XmlElement(name = "MinimumVoltage", required = false) val minimumVoltage: Double = 0.0
  @XmlElement(name = "MaximumVoltage", required = false) val maximumVoltage: Double = 0.0
}

class ProgramControlSettings {
  @XmlElement(name = "SkipLaserOnCheck", required = false) val skipLaserOnCheck: Int = 0
  @XmlElement(name = "AllowRemoteControl", required = false) val allowRemoteControl: Int = 0
}

class ChannelSettings {
  @XmlElement(name = "ChannelNumber", required = false) val channelNumber: Int = 0
  @XmlElement(name = "InternalFibreLength", required = false) val internalFibreLength: Double = 0.0
}

class TemperatureReferenceSettings {
  @XmlElement(name = "InternalReferenceStart", required = false) val internalReferenceStart: Double = 0.0
  @XmlElement(name = "InternalReferenceStop", required = false) val internalReferenceStop: Double = 0.0
  @XmlElement(name = "SamplingRate", required = false) val samplingRate: Double = 0.0
  @XmlElement(name = "UseReferenceResistor", required = false) val useReferenceResistor: Int = 0
  @XmlElement(name = "ReferenceResistor", required = false) val referenceResistor: Double = 0.0
  @XmlElement(name = "MaximumVoltage", required = false) val maximumVoltage: Double = 0.0
//  @XmlElement(name = "TemperatureProbeSettings", required = false) val digitalLine: java.util.List[TemperatureProbeSettings] = null
  @XmlElement(name = "TemperatureProbeSettings", required = false) val temperatureProbeSettings: java.util.List[TemperatureProbeSettings] = null
}

class TemperatureProbeSettings {
  @XmlElement(name = "Name", required = false) val name: String = ""
  @XmlElement(name = "DigitalLine", required = false) val digitalLine: java.util.List[DigitalLine] = null
  @XmlElement(name = "OffsetCoefficient", required = false) val offsetCoefficient: Double = 0.0
  @XmlElement(name = "SlopeCoefficient", required = false) val slopeCoefficient: Double = 0.0
  @XmlElement(name = "FixTemperature", required = false) val fixTemperature: Int = 0
  @XmlElement(name = "FixedTemperature", required = false) val fixedTemperature: Double = 0.0
}

class RawProcessingSettings {
  @XmlElement(name = "DAQSamplingFrequency", required = false) @XmlJavaTypeAdapter(classOf[BigDecimalAdapter]) val daqSamplingFrequency: java.math.BigDecimal = null
  @XmlElement(name = "EffectiveStokesRI", required = false) val effectiveStokesRI: Double = 0.0
  @XmlElement(name = "EffectiveAntiStokesRI", required = false) val effectiveAntiStokesRI: Double = 0.0
  @XmlElement(name = "CorrectForZigZag", required = false) val correctForZigZag: Int = 0
  @XmlElement(name = "LaserOnLength", required = false) val laserOnLength: Double = 0.0
}

class MeasurementSettings {
  @XmlElement(name = "InternalAveragingTime", required = false) val internalAveragingTime: Double = 0.0
  @XmlElement(name = "InternalDifferentialLoss", required = false) val internalDifferentialLoss: Double = 0.0
  @XmlElement(name = "TemperatureScalingFactor", required = false) val temperatureScalingFactor: Double = 0.0
  @XmlElement(name = "MaximumMeasurementLength", required = false) val maximumMeasurementLength: Double = 0.0
  @XmlElement(name = "SaveSignalData", required = false) val saveSignalData: Int = 0
}

class OvershootCorrectionSettings {
  @XmlElement(name = "CorrectForOvershoot", required = false) val correctForOvershoot: Int = 0
  @XmlElement(name = "Rotation", required = false) val rotation: Double = 0.0
  @XmlElement(name = "MultiplicationFactor", required = false) val multiplicationFactor: Double = 0.0
}

class CurveCalibrationSettings {
  @XmlElement(name = "StartTemperature", required = false) val startTemperature: Double = 0.0
  @XmlElement(name = "m", required = false) val m: Double = 0.0
  @XmlElement(name = "c", required = false) val c: Double = 0.0
}

class OperatingLimitsSettings {
  @XmlElement(name = "MinimumInputPower", required = false) val minimumInputPower: Double = 0.0
  @XmlElement(name = "MaximumInputPower", required = false) val maximumInputPower: Double = 0.0
  @XmlElement(name = "PowerHysteresis", required = false) val powerHysteresis: Double = 0.0
  @XmlElement(name = "MinimumInternalTemperature", required = false) val minimumInternalTemperature: Double = 0.0
  @XmlElement(name = "MaximumInternalTemperature", required = false) val maximumInternalTemperature: Double = 0.0
  @XmlElement(name = "TemperatureHysteresis", required = false) val temperatureHysteresis: Double = 0.0
}

class SAHSettings {
  @XmlElement(name = "DeviceType", required = false) val deviceType: Int = 0
  @XmlElement(name = "SAHCOMPort", required = false) val sahcomPort: String = ""
  @XmlElement(name = "DeviceYCOMPort", required = false) val deviceYCOMPort: String = ""
  @XmlElement(name = "MaximumPumpCurrent", required = false) val maximumPumpCurrent: Double = 0.0
  @XmlElement(name = "DefaultTargetVoltage", required = false) val defaultTargetVoltage: Double = 0.0
  @XmlElement(name = "WarmUpTime", required = false) val warmUpTime: Int = 0
  @XmlElement(name = "CoolDownTime", required = false) val coolDownTime: Int = 0
  @XmlElement(name = "TimingSettings", required = false) val TimingSettings: TimingSettings = null
}

class TimingSettings {
  @XmlElement(name = "MaintainSettings", required = false) val maintainSettings: TimingSettingsDetails = null
  @XmlElement(name = "FastSettings", required = false) val fastSettings: TimingSettingsDetails = null
  @XmlElement(name = "SuperFastSettings", required = false) val superFastSettings: TimingSettingsDetails = null
}

class TimingSettingsDetails {
  @XmlElement(name = "TimeBetweenSamples", required = false) val timeBetweenSamples: Int = 0
  @XmlElement(name = "NumberOfAverages", required = false) val numberOfAverages: Int = 0
  @XmlElement(name = "StepSize", required = false) val stepSize: Int = 0
  @XmlElement(name = "StateTransientDelay", required = false) val stateTransientDelay: Int = 0
}

class RangeSettings {
  @XmlElement(name = "MeasurementRange", required = false) val measurementRange: Double = 0
  @XmlElement(name = "LaserFrequency", required = false) val laserFrequency: Double = 0
  @XmlElement(name = "TargetVoltage", required = false) val targetVoltage: Double = 0
}

class PowerTimingSettings {
  @XmlElement(name = "OpticsOnWait", required = false) val opticsOnWait: Int = 0
  @XmlElement(name = "DAQPowerOnWait", required = false) val daqPowerOnWait: Int = 0
  @XmlElement(name = "DAQUSBOnWait", required = false) val daqUSBOnWait: Int = 0
  @XmlElement(name = "OpticsOffWait", required = false) val opticsOffWait: Int = 0
  @XmlElement(name = "DAQPowerOffWait", required = false) val daqPowerOffWait: Int = 0
  @XmlElement(name = "DAQUSBOffWait", required = false) val daqUSBOffWait: Int = 0
}

class UserConfiguration {
  @XmlAttribute(name = "softwareVersion", required = false) val softwareVersion: String = ""
  @XmlElement(name = "MainMeasurementConfiguration", required = false) val mainMeasurementConfiguration: MainMeasurementConfiguration = null
  @XmlElement(name = "ChannelConfiguration", required = false) val channelConfiguration: java.util.List[ChannelConfiguration] = null
}

class MainMeasurementConfiguration {
  @XmlElement(name = "ConfigurationName", required = false) val configurationName: String = ""
  @XmlElement(name = "ConfigurationComment", required = false) val configurationComment: String = ""
  @XmlElement(name = "MeasurementMethod", required = false) val measurementMethod: Int = 0
  @XmlElement(name = "NumberOfMeasurements", required = false) val numberOfMeasurements: Int = 0
  @XmlElement(name = "MeasurementInterval", required = false) val measurementInterval: Double = 0.0
  @XmlElement(name = "AutoRestart", required = false) val autoRestart: Int = 0
  @XmlElement(name = "TemperatureUnits", required = false) val temperatureUnits: Int = 0
  @XmlElement(name = "DistanceUnits", required = false) val distanceUnits: Int = 0
  @XmlElement(name = "MeasurementSystem", required = false) val measurementSystem: Int = 0
  @XmlElement(name = "LaserFrequency", required = false) val laserFrequency: Double = 0.0
}

class ChannelConfiguration {
  @XmlElement(name = "ChannelNumber", required = false) val channelNumber: Int = 0
  @XmlElement(name = "ChannelName", required = false) val channelName: String = ""
  @XmlElement(name = "ChannelIsActive", required = false) val channelIsActive: Int = 0
  @XmlElement(name = "SaveChannelData", required = false) val saveChannelData: Int = 0
  @XmlElement(name = "AcquisitionConfiguration", required = false) val acquisitionConfiguration: AcquisitionConfiguration = null
  @XmlElement(name = "TemperatureCalibrationConfiguration", required = false) val temperatureCalibrationConfiguration: TemperatureCalibrationConfiguration = null
  @XmlElement(name = "FibreCheckConfiguration", required = false) val fibreCheckConfiguration: FibreCheckConfiguration = null
  @XmlElement(name = "FibreCorrectionConfiguration", required = false) val fibreCorrectionConfiguration: FibreCorrectionConfiguration = null
}

class AcquisitionConfiguration {
  @XmlElement(name = "SamplingInterval", required = false) val samplingInterval: Int = 0
  @XmlElement(name = "MeasurementLength", required = false) val measurementLength: Double = 0.0
  @XmlElement(name = "AcquisitionTime", required = false) val acquisitionTime: Double = 0.0
}

class TemperatureCalibrationConfiguration {
  @XmlElement(name = "DifferentialLossConfiguration", required = false) val differentialLossConfiguration: DifferentialLossConfiguration = null
  @XmlElement(name = "TemperatureOffsetConfiguration", required = false) val temperatureOffsetConfiguration: TemperatureOffsetConfiguration = null
  @XmlElement(name = "ProbeConfiguration", required = false) val probeConfiguration: ProbeConfiguration = null
}

class DifferentialLossConfiguration {
  @XmlElement(name = "DifferentialLossCalculation", required = false) val differentialLossCalculation: Int = 0
  @XmlElement(name = "DifferentialLoss", required = false) val differentialLoss: Double = 0.0
  @XmlElement(name = "MatchedSection1Start", required = false) val matchedSection1Start: Double = 0.0
  @XmlElement(name = "MatchedSection2Start", required = false) val matchedSection2Start: Double = 0.0
  @XmlElement(name = "MatchedSectionLength", required = false) val matchedSectionLength: Double = 0.0
  @XmlElement(name = "DifferentialLossProbeSection", required = false) val differentialLossProbeSection: Int = 0
  @XmlElement(name = "PairedChannel", required = false) val pairedChannel: Int = 0
}

class TemperatureOffsetConfiguration {
  @XmlElement(name = "TemperatureOffsetCalculation", required = false) val temperatureOffsetCalculation: Int = 0
  @XmlElement(name = "TemperatureOffset", required = false) val temperatureOffset: Double = 0.0
  @XmlElement(name = "TemperatureOffsetProbeSection", required = false) val temperatureOffsetProbeSection: Int = 0
}

class ProbeConfiguration {
  @XmlElement(name = "Section1Start", required = false) val section1Start: Double = 0.0
  @XmlElement(name = "Section1Probe", required = false) val section1Probe: Int = 0
  @XmlElement(name = "Section2Start", required = false) val section2Start: Double = 0.0
  @XmlElement(name = "Section2Probe", required = false) val section2Probe: Int = 0
  @XmlElement(name = "ProbeSectionLength", required = false) val probeSectionLength: Double = 0.0
}

class FibreCheckConfiguration {
  @XmlElement(name = "UseFibreCheck", required = false) val useFibreCheck: Int = 0
}

class FibreCorrectionConfiguration {
  @XmlElement(name = "FibreOffset", required = false) val fibreOffset: Double = 0.0
  @XmlElement(name = "FibreStretch", required = false) val fibreStretch: Double = 0.0
}


















