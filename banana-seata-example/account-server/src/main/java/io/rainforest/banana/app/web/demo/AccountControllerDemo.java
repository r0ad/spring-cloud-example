package io.rainforest.banana.app.web.demo;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class AccountControllerDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountControllerDemo.class);

	private static final String SUCCESS = "SUCCESS";

	private static final String FAIL = "FAIL";

	private final JdbcTemplate jdbcTemplate;

	private Random random;

	public AccountControllerDemo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.random = new Random();
	}

	@PostMapping(value = "/account2", produces = "application/json")
	@GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
	public String account(String userId, int money) {
		LOGGER.info("Account Service ... xid: " + RootContext.getXID());

		int result = jdbcTemplate.update(
				"update account_tbl set money = money - ? where user_id = ?",
				new Object[] { money, userId });

		if (random.nextBoolean()) {
			throw new RuntimeException("this is a mock Exception");
		}

		LOGGER.info("Account Service End ... ");
		if (result == 1) {
			return SUCCESS;
		}
		return FAIL;
	}

}
