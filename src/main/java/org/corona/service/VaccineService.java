package org.corona.service;

import java.io.IOException;
import java.util.ArrayList;

import org.corona.domain.VaccionVO;


public interface VaccineService {

	ArrayList<VaccionVO> vaccionApi(String start, String doo,String dog) throws IOException;



}
