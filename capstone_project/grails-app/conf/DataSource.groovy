dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "grails"
    password = "server"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}

// environment specific settings
environments {
	development {
		dataSource {
			// one of 'create', 'create-drop','update'
			dbCreate = "create-drop"
			// NOTE: the JDBC connection string should be
			// all on the same line.
			url = "jdbc:mysql://localhost:3306/omg_dev?autoreconnect=true"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost:3306/omg_dev?autoreconnect=true"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost:3306/omg_dev?autoreconnect=true"
		}
	}
}
