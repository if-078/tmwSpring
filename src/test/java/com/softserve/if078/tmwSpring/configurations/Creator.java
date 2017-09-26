package com.softserve.if078.tmwSpring.configurations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Creator {

	@Autowired
	DataSource datasource;

	public void initDb() {
		try {
			File f = new File("src\\test\\resources\\h2_script.sql");
			FileReader reader = null;
			try {
				reader = new FileReader(f);

			} catch (FileNotFoundException ex) {
				System.err.println("File not found excp");
			}
			RunScript.execute(datasource.getConnection(), reader);
		} catch (SQLException ex) {
			System.err.println("Some exp in script");
		}
	}

}
