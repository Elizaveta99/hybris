package concerttours.jobs;

import concerttours.model.TokenCronJobModel;
import concerttours.model.TokenItemModel;
import concerttours.service.TokenItemService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.log4j.Logger;

import java.security.SecureRandom;

public class GenerateTokenJobPerformable extends AbstractJobPerformable<TokenCronJobModel> {

    private static final Logger LOG = Logger.getLogger(GenerateTokenJobPerformable.class);

    private TokenItemService tokenItemService;

    public void setTokenItemService(TokenItemService tokenItemService) {
        this.tokenItemService = tokenItemService;
    }

    @Override
    public PerformResult perform(TokenCronJobModel tokenCronJobModel) {
        LOG.info("Token generating");
        TokenItemModel tokenItemModel = tokenItemService.getTokenItems().get(0);
        tokenItemModel.setToken(generateToken());
        modelService.save(tokenItemModel);
        LOG.info("New Token: " + tokenItemModel.getToken());
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }


    private String generateToken() {
        return Long.toString(Math.abs(new SecureRandom().nextLong()),16 );
    }

}
