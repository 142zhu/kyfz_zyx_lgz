package com.gzlzinfo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.csvreader.CsvReader;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.kyfz.service.IKyfzEnterpriseService;
import com.ruoyi.kyfz.service.IKyfzExpertService;
import com.ruoyi.kyfz.service.IKyfzMatchService;
import com.ruoyi.kyfz.service.IKyfzRequirementService;

@SpringBootTest(classes = RuoYiApplication.class)
public class TestImport {

    @Autowired
    private IKyfzExpertService expertService;

    @Autowired
    private IKyfzEnterpriseService enterpriseService;

    @Autowired
    private IKyfzMatchService matchService;

    @Autowired
    private IKyfzRequirementService requirementService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String PREFIX_PATH = "D:/VSCode/KYFZ/kyfz_zyx_lgz/newTable/";

    private static final String SQL_MODE_SQL = "SET GLOBAL sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION,NO_AUTO_VALUE_ON_ZERO'";

    @Test
    public void testImport() {
        // importExpert("专家表_0722.csv");
        // importEnterprise("企业表_0722.csv");
        // importMatch("匹配表.csv");
        // importRequirements("需求表.csv");
        // importTeam("团队表.csv");
        // importWork("著作表.csv");
        importThesis("论文表.csv");
        // importCertificate("证书表.csv");
        // importProject("项目表.csv");
        // importRelationship("关系表.csv");
        // importAward("奖项表.csv");
        // importOtherResults("其他成果表.csv");
        // importIndustryClassification("行业分类表_0722.csv");
        // importIntellectualProperty("知识产权表.csv");
    }

    /**
     * 导入专家数据
     *
     * @param fileName
     */
    private void importExpert(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        jdbcTemplate.execute(SQL_MODE_SQL);
        String sql = "insert into `kyfz_expert` (`expert_id`, `expert_account`, `expert_name`, `expert_position`, `expert_affiliation`,"
                +
                " `primary_discipline`, `secondary_discipline`, `tertiary_discipline`, `research_direction`, `thesis_id`, `project_id`,"
                +
                " `intellectual_property_id`, `awards_id`,`other_results_id`, `requirement_id`, `expert_teams_id`,`category_id`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        for (String[] el : list) {
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4], el[5], el[6], el[7], el[8], el[9], el[10],
                    el[11], el[12], el[13], el[14], el[15], el[16]);
        }
    }

    /**
     * 导入企业数据
     *
     * @param fileName
     */
    private void importEnterprise(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        jdbcTemplate.execute(SQL_MODE_SQL);
        String sql = "insert into `kyfz_enterprise` (`enterprise_id`, `enterprise_name`, `enterprise_credit_code`, `registered_capital`, "
                +
                "`category_id`,`enterprise_keywords` ) values (?,?,?,?,?,?);";
        for (String[] el : list) {
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4], el[5]);
            // KyfzEnterprise enterprise = new KyfzEnterprise();
            // enterprise.setEnterpriseId(Long.parseLong(el[0]));
            // enterprise.setEnterpriseName(el[1]);
            // enterprise.setEnterpriseCreditCode(el[2]);
            // enterprise.setEnterpriseDescribe(el[3]);
            // enterprise.setRegisteredCapital(el[4]);
            // enterpriseService.insertKyfzEnterprise(enterprise);
        }
    }

    /**
     * 导入匹配信息
     *
     * @param fileName
     */
    private void importMatch(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        jdbcTemplate.execute(SQL_MODE_SQL);
        String sql = "insert into `kyfz_match` (`expert_account`, `requirement_id`, `match_score`, `thesis_id`, `work_id`, `project_id`,`certificate_id` ) "
                +
                "values (?,?,?,?,?,?,?);";
        for (String[] el : list) {
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4], el[5], el[6]);
            // KyfzMatch match = new KyfzMatch();
            // match.setExpertAccount(el[0]);
            // match.setRequirementId(el[1]);
            // match.setMatchScore(el[2]);
            // match.setThesisId(el[3]);
            // match.setWorkId(el[4]);
            // match.setProjectId(el[5]);
            // match.setCertificateId(el[6]);
            // matchService.insertKyfzMatch(match);
        }
    }

    /**
     * 导入团队
     *
     * @param fileName
     */
    private void importTeam(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        jdbcTemplate.execute(SQL_MODE_SQL);

        String sql = "insert into `kyfz_team` (`team_id`,`team_members`,`team_account`,`member_scores`,`reseach_directions`) values (?,?,?,?,?);";
        for (String[] el : list) {
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4]);
        }
    }

    /**
     * 导入需求
     *
     * @param fileName
     */
    private void importRequirements(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        jdbcTemplate.execute(SQL_MODE_SQL);
        String sql = "insert into `kyfz_requirement` (`requirement_id`, `project_name`, `contract_number`, `client`," +
                " `project_leader`, `project_leader_job_number`, `project_members`, `support_unit`, `support_unit_number`,"
                +
                " `project_category`, `project_type`, `project_classification`, `project_nature`, `service_national_economy`,"
                +
                " `national_economy_sub_industry`, `socio_economic_objective`, `start_project_time`, `end_project_time`, `total_budget`,"
                +
                " `contract_type`, `first_discipline`, `secondary_discipline`, `project_leader_unit`,"
                +
                " `campus_member_account`, `requirement_status`, `enterprise_number`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        for (String[] el : list) {
            Date date = null, date1 = null;
            try {
                String startDateStr = el[16], endDateStr = el[17];
                if (StringUtils.isNotBlank(startDateStr)) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
                }
                if (StringUtils.isNotBlank(endDateStr)) {
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
                }

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4], el[5], el[6], el[7], el[8], el[9], el[10],
                    el[11], el[12], el[13], el[14], el[15], date, date1, el[18], el[19], el[20], el[21], el[22], el[23],
                    el[24], el[25]);
            // KyfzRequirement requirement = new KyfzRequirement();
            // requirement.setProjectName(el[1]);
            // requirement.setContractNumber(el[2]);
            // requirement.setClient(el[3]);
            // requirement.setProjectLeader(el[4]);
            // requirement.setProjectLeaderJobNumber(el[5]);
            // requirement.setProjectMembers(el[6]);
            // requirement.setSupportUnit(el[7]);
            // requirement.setSupportUnitNumber(el[8]);
            // requirement.setProjectCategory(el[9]);
            // requirement.setProjectType(el[10]);
            // requirement.setProjectClassification(el[11]);
            // requirement.setProjectNature(el[12]);
            // requirement.setServiceNationalEconomy(el[13]);
            // requirement.setNationalEconomySubIndustry(el[14]);
            // requirement.setSocioEconomicObjective(el[15]);
            // //TODO:
            // try {
            // String startDateStr = el[16], endDateStr = el[17];
            // if (StringUtils.isNotBlank(startDateStr)) {
            // Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
            // requirement.setStartProjectTime(startDate);
            // }
            // if (StringUtils.isNotBlank(endDateStr)) {
            // Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
            // requirement.setEndProjectTime(endDate);
            // }
            //
            // } catch (ParseException e) {
            // throw new RuntimeException(e);
            // }
            // requirement.setTotalBudget(el[18]);
            // requirement.setContractType(el[19]);
            // requirement.setFirstDiscipline(el[20]);
            // requirement.setSecondaryDiscipline(el[21]);
            // requirement.setProjectLeaderUnit(el[22]);
            // requirement.setEnterpriseNumber(el[23]);
            // requirement.setCampusMemberAccount(el[24]);
            // requirement.setRequirementStatus(el[25]);
            // requirementService.insertKyfzRequirement(requirement);
        }
    }

    /**
     * 导入著作
     *
     * @param fileName
     */
    private void importWork(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        String sql = "insert into `kyfz_work` (`work_id`,`work_name`,`work_type`,`ISBN`,`editor`,`publish_time`,`total_words`,`country_region`,"
                +
                "`language`,`publisher`,`full_text_url`,`signature_unit`,`second_unit_code`,`second_unit_name`,`other_signature_unit`,"
                +
                "`member_info`,`member_account`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.execute(SQL_MODE_SQL);
        for (String[] el : list) {
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4], el[5], el[6], el[7], el[8], el[9], el[10],
                    el[11], el[12], el[13], el[14], el[15], el[16]);
        }
    }

    /**
     * 导入论文
     *
     * @param fileName
     */
    private void importThesis(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        String sql = "insert into `kyfz_thesis` (`thesis_id`,`thesis_name`,`thesis_type`,`publicate_time`,`thesis_abstract`,`thesis_keywords`,`thesis_journal`,"
                +
                "`journal_level`,`thesis_author`,`school_author_account`) values (?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.execute(SQL_MODE_SQL);
        for (String[] el : list) {
            String dateStr = el[3];
            Date date = null;
            if (StringUtils.isNotBlank(dateStr)) {
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            jdbcTemplate.update(sql, el[0], el[1], el[2], date, el[4], el[5], el[6], el[7], el[8], el[9]);
        }
    }

    /**
     * 导入证书
     *
     * @param fileName
     */
    private void importCertificate(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        String sql = "insert into `kyfz_certificate` (`certificate_id`,`certificate_name`,`achievement_type`,`certificate_number`,`country_region`,`application_date`,"
                +
                "`approval_date`,`patent_type`,`patent_status`,`standard_type`,`signature_unit`,`second_unit_code`,`second_unit_name`,"
                +
                "`other_signature_unit`,`member_info`,`member_account`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.execute(SQL_MODE_SQL);
        for (String[] el : list) {
            String dateStr = el[5], dateStr1 = el[6];
            Date date = null, date1 = null;
            try {
                if (StringUtils.isNotBlank(dateStr)) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                }
                if (StringUtils.isNotBlank(dateStr1)) {
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr1);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4], date, date1, el[7], el[8], el[9], el[10],
                    el[11], el[12], el[13], el[14], el[15]);
        }
    }

    /**
     * 导入项目
     *
     * @param fileName
     */
    private void importProject(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        String sql = "insert into `kyfz_project` (`project_id`,`project_name`,`project_type`,`project_category`,`start_date`,"
                +
                "`project_status`,`project_level`,`approval_number`,`end_date`,`start_funds`,`account_funds`,`start_time`,"
                +
                "`end_time`,`sign_unit`,`other_sign_unit`,`dept`,`remark`,`member_info`,`data_source`,`school_member_account`) "
                +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.execute(SQL_MODE_SQL);
        for (String[] el : list) {
            String dateStr = el[4], dateStr1 = el[8], dateStr2 = el[11], dateStr3 = el[12];
            Date date = null, date1 = null, date2 = null, date3 = null;
            try {
                if (StringUtils.isNotBlank(dateStr)) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                }
                if (StringUtils.isNotBlank(dateStr1)) {
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr1);
                }
                if (StringUtils.isNotBlank(dateStr2)) {
                    date2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr2);
                }
                if (StringUtils.isNotBlank(dateStr3)) {
                    date3 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr3);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], date, el[5], el[6], el[7], date1, el[9], el[10], date2,
                    date3, el[13], el[14], el[15], el[16], el[17], el[18], el[19]);
        }
    }

    /**
     * 导入关系表
     *
     * @param fileName
     */
    private void importRelationship(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        String sql = "insert into `kyfz_relationship` (`relationship_id`,`expert_A_id`,`expert_B_id`,`relationship_type`,`relationship_description`,`relationship_value`) values (?,?,?,?,?,?);";
        jdbcTemplate.execute(SQL_MODE_SQL);
        for (String[] el : list) {
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4], el[5]);
        }
    }

    /**
     * 导入奖项
     *
     * @param fileName
     */
    private void importAward(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        String sql = "insert into `kyfz_award` (`award_id`,`award_category`,`award_project`,`types_award_projects`,`award_name`,"
                +
                "`award_date`,`reward_level`,`reward_level_segmentation`,`awarding_country`,`awarding_unit`,`reward_method`,`signature_unit`,"
                +
                "`belongs_secondary_unit_code`,`name_secondary_unit`,`other_sign_unit`,`reason_award`,`remark`,`performance_status`,`member_information`,`school_member_account`) "
                +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.execute(SQL_MODE_SQL);
        for (String[] el : list) {
            String dateStr = el[5];
            Date date = null;
            try {
                if (StringUtils.isNotBlank(dateStr)) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4], date, el[6], el[7], el[8], el[9], el[10],
                    el[11], el[12], el[13], el[14], el[15], el[16], el[17], el[18], el[19]);
        }
    }

    /**
     * 导入其他成果
     *
     * @param fileName
     */
    private void importOtherResults(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        String sql = "insert into `kyfz_other_results` (`other_results_id`,`outcome_name`,`outcome_type`,`date`,`name_secondary_unit`,"
                +
                "`member_information`,`school_member_account`)"
                +
                "values (?,?,?,?,?,?,?);";
        jdbcTemplate.execute(SQL_MODE_SQL);
        for (String[] el : list) {
            String dateStr = el[3];
            Date date = null;
            try {
                if (StringUtils.isNotBlank(dateStr)) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            jdbcTemplate.update(sql, el[0], el[1], el[2], date, el[4], el[5], el[6]);
        }
    }

    /**
     * 导入行业分类
     *
     * @param fileName
     */
    private void importIndustryClassification(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        String sql = "insert into `kyfz_industry_classification` (`category_id`,`category_name`,`parent_id`,`level`)"
                +
                "values (?,?,?,?);";
        jdbcTemplate.execute(SQL_MODE_SQL);
        for (String[] el : list) {
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3]);
        }
    }

    /**
     * 导入知识产权
     *
     * @param fileName
     */
    private void importIntellectualProperty(String fileName) {
        List<String[]> list = readByCsvReader(PREFIX_PATH + fileName);
        String sql = "insert into `kyfz_intellectual_property` (`intellectual_property_id`,`intellectual_property_name`,`performance_category`,`certificate_number`,`Country_or_region`,"
                +
                "`application_date`,`approval_date`,`patent_type`,`patent_status`,`standard_type`,`signature_unit`,`belongs_secondary_unit_code`,"
                +
                "`name_secondary_unit`,`other_sign_unit`,`member_information`,`school_member_account`) "
                +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.execute(SQL_MODE_SQL);
        for (String[] el : list) {
            String dateStr = el[5], dateStr1 = el[6];
            Date date = null, date2 = null;
            try {
                if (StringUtils.isNotBlank(dateStr)) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                }
                if (StringUtils.isNotBlank(dateStr)) {
                    date2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr1);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            jdbcTemplate.update(sql, el[0], el[1], el[2], el[3], el[4], date, date2, el[7], el[8], el[9], el[10],
                    el[11], el[12], el[13], el[14], el[15]);
        }
    }

    /**
     * 读取csv数据
     *
     * @param filePath
     * @return
     */
    public List<String[]> readByCsvReader(String filePath) {
        CsvReader csvReader;
        try {
            csvReader = new CsvReader(filePath, ',', Charset.defaultCharset());
            csvReader.readHeaders(); // 读取表头
            List<String[]> values = new ArrayList<>();
            while (csvReader.readRecord()) {
                // 读取一行数据
                String[] vs = csvReader.getValues();
                values.add(vs);
            }
            csvReader.close();
            return values;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
