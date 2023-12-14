package com.untucapital.cms.config;

import com.untucapital.cms.auth.AuthEntryPoint;
import com.untucapital.cms.auth.AuthFilter;
import com.untucapital.cms.auth.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig {

    private final UserPrincipalService userPrincipalService;
    private final AuthEntryPoint authEntryPoint;

    @Autowired
    public WebSecurityConfig(UserPrincipalService userPrincipalService, AuthEntryPoint authEntryPoint) {
        this.userPrincipalService = userPrincipalService;
        this.authEntryPoint = authEntryPoint;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userPrincipalService);
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .requestMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .requestMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/webjars/**")
                .permitAll()
                .requestMatchers("/auth/**")
                .permitAll()
                .requestMatchers("/direct_cost/**")
                .permitAll()
                .requestMatchers("/period_of_analysis/**")
                .permitAll()
                .requestMatchers("/stock/**")
                .permitAll()
                .requestMatchers("/operational_cost/**")
                .permitAll()
                .requestMatchers("/users/**")
                .permitAll()
                .requestMatchers("/credit_application/**")
                .permitAll()
                .requestMatchers("/credit_app/**")
                .permitAll()
                .requestMatchers("/cms_authorisation/**")
                .permitAll()
                .requestMatchers("/industries/**")
                .permitAll()
                .requestMatchers("/uploadFile/**")
                .permitAll()
                .requestMatchers("/uploadMultipleFiles/**")
                .permitAll()
                .requestMatchers("/downloadFile/**")
                .permitAll()
                .requestMatchers("/downloadFiles/**")
                .permitAll()
                .requestMatchers("/business/**")
                .permitAll()
                .requestMatchers("/cities/**")
                .permitAll()
                .requestMatchers("/deleteUser/**")
                .permitAll()
                .requestMatchers("/branches/**")
                .permitAll()
                .requestMatchers("/branches/**")
                .permitAll()
                .requestMatchers("/meetings/**")
                .permitAll()
                .requestMatchers("/role/**")
                .permitAll()
                .requestMatchers("/inventories/**")
                .permitAll()
                .requestMatchers("/roles/**")
                .permitAll()
                .requestMatchers("/disbursementTickets/**")
                .permitAll()
                .requestMatchers("/appraisalLoanRequest/**")
                .permitAll()
                .requestMatchers("/sales/**")
                .permitAll()
                .requestMatchers("/signature/**")
                .permitAll()
                .requestMatchers("/businessUnit/**")
                .permitAll()
                .requestMatchers("/comments/**")
                .permitAll()
                .requestMatchers("/signatures/**")
                .permitAll()
                .requestMatchers("/credit_history/**")
                .permitAll()
                .requestMatchers("/disbursementTickets/**")
                .permitAll()
                .requestMatchers("/loanRequest/**")
                .permitAll()
                .requestMatchers("/long_term_credit_history/**")
                .permitAll()
                .requestMatchers("/main_competitor/**")
                .permitAll()
                .requestMatchers("/mitigating_risk/**")
                .permitAll()
                .requestMatchers("/most_important_clients/**")
                .permitAll()
                .requestMatchers("/ownership_details/**")
                .permitAll()
                .requestMatchers("/risk_analysis/**")
                .permitAll()
                .requestMatchers("/source_of_funds/**")
                .permitAll()
                .requestMatchers("/most_important_suppliers/**")
                .permitAll()
                .requestMatchers("/bank/**")
                .permitAll()
                .requestMatchers("/bankTo/**")
                .permitAll()
                .requestMatchers("/currentAssets/**")
                .permitAll()
                .requestMatchers("/shortTermLiability/**")
                .permitAll()
                .requestMatchers("/fixedAssets/**")
                .permitAll()
                .requestMatchers("/longTermLiability/**")
                .permitAll()
                .requestMatchers("/interestAndFinancialCost/**")
                .permitAll()
                .requestMatchers("/collateralSecurity/**")
                .permitAll()
                .requestMatchers("/capitalisationOfProfit/**")
                .permitAll()
                .requestMatchers("/financialOutflow/**")
                .permitAll()
                .requestMatchers("/financialInflow/**")
                .permitAll()
                .requestMatchers("/investmnetInflow/**")
                .permitAll()
                .requestMatchers("/investmentOutflow/**")
                .permitAll()
                .requestMatchers("/faqs/**")
                .permitAll()
                .requestMatchers("/news/**")
                .permitAll()
                .requestMatchers("/testimonials/**")
                .permitAll()
                .requestMatchers("/directors/**")
                .permitAll()
                .requestMatchers("/teams/**")
                .permitAll()
                .requestMatchers("/website-images/**")
                .permitAll()
                .requestMatchers("/contactEmail/**")
                .permitAll()
                .requestMatchers("/generalBusinessInformation/**")
                .permitAll()
                .requestMatchers("/businessAndPersonalReference/**")
                .permitAll()
                .requestMatchers("/otherBusinessAndIncome/**")
                .permitAll()
                .requestMatchers("/repaymentHistory/**")
                .permitAll()
                .requestMatchers("/swotRiskAnalysis/**")
                .permitAll()
                .requestMatchers("/clientCharacterAssessment/**")
                .permitAll()
                .requestMatchers("/familyUnitAndPersonalExpenses/**")
                .permitAll()
                .requestMatchers("/privateExpenses/**")
                .permitAll()
                .requestMatchers("/commentsOnFamilySituation/**")
                .permitAll()
                .requestMatchers("/salesPurchasesCogsGrossMargin/**")
                .permitAll()
                .requestMatchers("/accountsReceivables/**")
                .permitAll()
                .requestMatchers("/commentsOnAccountReceivables/**")
                .permitAll()
                .requestMatchers("/fixedAssets/**")
                .permitAll()
                .requestMatchers("/fixedAssetsMicro/**")
                .permitAll()
                .requestMatchers("/commentsOnFixedAssets/**")
                .permitAll()
                .requestMatchers("/inventory/**")
                .permitAll()
                .requestMatchers("/guarantor/**")
                .permitAll()
                .requestMatchers("/operationalExpenses/**")
                .permitAll()
                .requestMatchers("/balanceSheet/**")
                .permitAll()
                .requestMatchers("/liabilities/**")
                .permitAll()
                .requestMatchers("/applicationOfFunds/**")
                .permitAll()
                .requestMatchers("/clientRequest/**")
                .permitAll()
                .requestMatchers("/loanOfficerProposal/**")
                .permitAll()
                .requestMatchers("/dailySales/**")
                .permitAll()
                .requestMatchers("/averageDailySales/**")
                .permitAll()
                .requestMatchers("/past3WorkingDays/**")
                .permitAll()
                .requestMatchers("/past3Months/**")
                .permitAll()
                .requestMatchers("/cashOnHand/**")
                .permitAll()
                .requestMatchers("/seasonalityOfSales/**")
                .permitAll()
                .requestMatchers("/purchaseBehaviour/**")
                .permitAll()
                .requestMatchers("/mainProductPurchases/**")
                .permitAll()
                .requestMatchers("/pastPurchases/**")
                .permitAll()
                .requestMatchers("/motorVehicle/**")
                .permitAll()
                .requestMatchers("/realEstate/**")
                .permitAll()
                .requestMatchers("/houseHoldAssets/**")
                .permitAll()
                .requestMatchers("/businessAssets/**")
                .permitAll()
                .requestMatchers("/inventoryTwo/**")
                .permitAll()
                .requestMatchers("/guarantorAssessmentResults/**")
                .permitAll()
                .requestMatchers("/capacityAssessment/**")
                .permitAll()
                .requestMatchers("/capacityAssessment2/**")
                .permitAll()
                .requestMatchers("/loanRequestMicro/**")
                .permitAll()
                .requestMatchers("/costOfSales/**")
                .permitAll()
                .requestMatchers("/currentAssetsMicro/**")
                .permitAll()
                .requestMatchers("/loanRequestMicro/**")
                .permitAll()
                .requestMatchers("/guarantorAssessmentDetails/**")
                .permitAll()
                .requestMatchers("/assessmentFileUpload/**")
                .permitAll()
                .requestMatchers("/appraisalFileUpload/**")
                .permitAll()
                .requestMatchers("/ClientFileUpload/**")
                .permitAll()
                .requestMatchers("/xdsFileUpload/**")
                .permitAll()
                .requestMatchers("/musoni/**")
                .permitAll()
                .requestMatchers("/musoni_pastel/**")
                .permitAll()
                .requestMatchers("/credit_application_enquiry/**")
                .permitAll()
                .requestMatchers("/amortize/**")
                .permitAll()
                .requestMatchers("/requestCollateralSecurity/**")
                .permitAll()

                .requestMatchers("/credit_application_feedback/**")
                .permitAll()

                .requestMatchers("/sms/**")
                .permitAll()

                .requestMatchers("/credit_application_pipeline/**")
                .permitAll()

                .requestMatchers("/leadStatus/**")
                .permitAll()

                .requestMatchers("/zones/**")
                .permitAll()

                .requestMatchers("/events/**")
                .permitAll()

                .requestMatchers("/market_campaigns/**")
                .permitAll()

                .requestMatchers("/followUpDiary/**")
                .permitAll()

                .requestMatchers("/marketLeads/**")
                .permitAll()

                .requestMatchers("/credit_application_client_datasets/**")
                .permitAll()

                .requestMatchers("/access_logs/**")
                .permitAll()

                .requestMatchers("/requisitions/**")
                .permitAll()

                .requestMatchers("/poTransactions/**")
                .permitAll()

                .requestMatchers("/pos/**")
                .permitAll()

                .requestMatchers("/cms/**")
                .permitAll()

                .anyRequest()
                .authenticated();


        httpSecurity.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
