package com.springboot.corporative.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.corporative.document.Account;
import com.springboot.corporative.document.Corporative;
import com.springboot.corporative.dto.CorporativeDto;

@Component
public class UtilConvert {

	public Corporative convertPyme(CorporativeDto CorporativeDto) {
		
		
		Account account = new Account();
		
		account.setIdAccount(CorporativeDto.getIdAccount());
        account.setNameAccount(CorporativeDto.getNameAccount());
        account.setNumberAccount(CorporativeDto.getNumberAccount());
        
        List<Account> lista = new ArrayList<>();
        lista.add(account);
        
        Corporative Pyme = new Corporative();

	    Pyme.setTipoDoc(CorporativeDto.getTipoDoc());
	    Pyme.setNumDoc(CorporativeDto.getNumDoc());
	    Pyme.setName(CorporativeDto.getName()); 
	    Pyme.setAddress(CorporativeDto.getAddress());
	    Pyme.setCreateDate(new Date());
	    Pyme.setUpdateDate(new Date());
	    Pyme.setListAccount(lista);

	    return 	Pyme;
	  }
}
