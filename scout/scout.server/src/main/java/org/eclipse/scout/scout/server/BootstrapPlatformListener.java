package org.eclipse.scout.scout.server;

import org.eclipse.scout.rt.platform.IPlatform;
import org.eclipse.scout.rt.platform.IPlatformListener;
import org.eclipse.scout.rt.platform.PlatformEvent;
import org.eclipse.scout.rt.platform.security.SimplePrincipal;
import org.eclipse.scout.rt.platform.transaction.TransactionScope;
import org.eclipse.scout.rt.server.context.ServerRunContext;
import org.eclipse.scout.rt.server.context.ServerRunContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.Subject;
import java.util.Locale;
import java.util.concurrent.Callable;

public class BootstrapPlatformListener implements IPlatformListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapPlatformListener.class);

  @Override
  public void stateChanged(PlatformEvent event) {
    if (event.getState() == IPlatform.State.PlatformStarted) {
      Subject subject = new Subject();
      subject.getPrincipals().add(new SimplePrincipal("system"));
      subject.setReadOnly();

      ServerRunContext runContext = ServerRunContexts.empty()
        .withTransactionScope(TransactionScope.REQUIRES_NEW)
        .withSubject(subject)
        .withLocale(Locale.GERMAN);

      runContext.call(new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
          //FIXME: scout test setup is currently not configured to run with a db - db-migration-execution is prevented by this
          if(!event.getSource().getClass().getSimpleName().equals("GlobalTestingPlatform")) {
//            bootstrap();
          }
          return true;
        }
      });
    }
  }

/*  private void bootstrap() {
    // preload postgres service to have log messages in right order
    BEANS.get(PostgresSqlService.class);

    ConsistencyCheck.runConsistencyCheck();
    if (BooleanUtility.nvl(CONFIG.getPropertyValue(RunMigrationProperty.class), true)) {
      DbMigration.runDbMigration();
    }
    else {
      LOGGER.info("Skipping DB Migration - The property 'com.bsivet.database.runmigration' is set to 'false'");
    }

    BEANS.get(EntityCache.class).clearAll();
    scheduleJobs();
  }

  private void scheduleJobs() {
    LOGGER.info("IsAbvJob and DayliAccountsJobs are scheduled");

    Jobs.schedule(new IsAbvJob(),
      Jobs.newInput()
        .withName("IS ABV request job")
        .withRunContext(ServerRunContexts.copyCurrent(true))
        .withExecutionTrigger(Jobs.newExecutionTrigger()
          .withSchedule(CronScheduleBuilder.monthlyOnDayAndHourAndMinute(1, 2, 0))));

    Jobs.schedule(new DailyAccountsJob(),
      Jobs.newInput()
        .withName("daily accounts creation")
        .withRunContext(ServerRunContexts.copyCurrent(true))
        .withExecutionTrigger(Jobs.newExecutionTrigger()
          .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(23, 58))));
  }*/

}
