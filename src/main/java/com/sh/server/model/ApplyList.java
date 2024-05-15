package com.sh.server.model;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ApplyList {
    public void applylistToFile(Map<Integer, List<Course>> applyList) {
        File file = new File("/Users/jeong-yejin/2024SSGI&C/oop2-oops-enrollment-jyj/src", "applylist.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(applyList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, List<Course>> fileToApplyList() {
        File file = new File("/Users/jeong-yejin/2024SSGI&C/oop2-oops-enrollment-jyj/src", "applylist.ser");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            return (Map<Integer, List<Course>>) obj;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("저장된 수강신청이 없습니다.");
            return null;
        }
    }

//    public List<Course> findApplyList(int stdId, Map<Integer, List<Course>> applyList) {
//        return applyList.get(stdId);
//    }
}
