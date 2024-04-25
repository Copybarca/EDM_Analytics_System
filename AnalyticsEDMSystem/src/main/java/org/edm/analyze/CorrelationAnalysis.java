package org.edm.analyze;

import org.edm.dao.SensorIndicatorDAO;
import org.edm.models.Detail;
import org.edm.models.Sensor;
import org.edm.models.SensorIndicator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CorrelationAnalysis {
    ArrayList<Sensor> sensorList; // id, type, machines_id
    ArrayList<Sensor> tempList;
    ArrayList<SensorIndicator> tempIndicatorList;
    ArrayList<Sensor> vibroList;
    ArrayList<SensorIndicator> vibroIndicatorList;
    ArrayList<Sensor> flowList;
    ArrayList<SensorIndicator> flowIndicatorList;
    ArrayList<Sensor> condList;
    ArrayList<SensorIndicator> condIndicatorList; // date, time, id, sensors_id,value
    ArrayList<Detail> detailList;
    SensorIndicatorDAO sensorIndicatorDAO;

    public CorrelationAnalysis(){
    }

    public CorrelationAnalysis(ArrayList<Sensor> sensorList, ArrayList<Detail> detailList, @Autowired SensorIndicatorDAO sensorIndicatorDAO){//список датчиков должен быть привязан к конкретному станку. На вход подаётся тольяко список датчиков определённого станка и список деталей определённого станка
        this.sensorList = sensorList;
        this.detailList = detailList;
        this.sensorIndicatorDAO=sensorIndicatorDAO;
        sortBySensorType(sensorList);
        sortIndicatorsBySensorId();
    }


    private void sortBySensorType(ArrayList<Sensor> listToSort){//список всех датчиков станка разделяется на подсписки датчиков конкретного типа
        for(Sensor sensor: listToSort){
            switch(sensor.getTitle().toLowerCase()){
                case"vibration":vibroList.add(sensor);
                continue;
                case"temperature":tempList.add(sensor);
                continue;
                case"flow":flowList.add(sensor);
                continue;
                case"conductivity":condList.add(sensor);
            }
        }
    }
    private void sortIndicatorsBySensorId(){// получаем объекты показатели data time value id sensor_id
        for(Sensor sensor: vibroList){
            vibroIndicatorList= (ArrayList<SensorIndicator>) sensorIndicatorDAO.indexBySensorId(sensor.getId());
        }
        for(Sensor sensor: tempList){
            tempIndicatorList= (ArrayList<SensorIndicator>) sensorIndicatorDAO.indexBySensorId(sensor.getId());
        }
        for(Sensor sensor: condList){
            condIndicatorList= (ArrayList<SensorIndicator>) sensorIndicatorDAO.indexBySensorId(sensor.getId());
        }
        for(Sensor sensor: flowList){
            flowIndicatorList= (ArrayList<SensorIndicator>) sensorIndicatorDAO.indexBySensorId(sensor.getId());
        }
    }
    public Integer commonValueOfIndicatorList(ArrayList<SensorIndicator> list){//принимает список показателей сенсора и возвращает среднее значение
        int sumValue = 0;
        int counter = 0;
        for(SensorIndicator indicator:list){
            sumValue +=indicator.getValue();
            counter++;
        }
        int commonValue = sumValue/counter;
        return (Integer)commonValue;
    }
    public Integer qualityPercentOfDetail(ArrayList<Detail> list){//принимает список деталей и возвращает процент качественных деталей
        int counterQuality = 0;
        int counterDefective = 0;
        for (Detail detail:list){
            if(detail.getQuality()==1){
                counterQuality++;
            }else{
                counterDefective++;
            }
        }
        return ( counterQuality/(counterQuality+counterDefective) )*100;
    }


}
