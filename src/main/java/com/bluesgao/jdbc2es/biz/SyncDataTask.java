package com.bluesgao.jdbc2es.biz;

import com.bluesgao.jdbc2es.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveTask;

/**
 * ClassName: SyncDataTask
 * Desc: TODO
 * Author: gaoxin11
 * Date: 2018/6/13 15:46
 **/
public class SyncDataTask extends RecursiveTask<Long> {
    public static final Logger LOGGER = LoggerFactory.getLogger(SyncDataTask.class);

    private Long start;
    private Long end;
    private UserService userService;
    private final Long STEP = 500L;

    public SyncDataTask(Long start, Long end, UserService userService) {
        this.start = start;
        this.end = end;
        this.userService = userService;
    }


    @Override
    protected Long compute() {
        Long sum = 0L;

        //如果大于 step，则拆分任务
        if ((end - start) > STEP) {
            LOGGER.info("符合拆分条件:start-{}，end-{}，step-{}", start, end, STEP);
            Long middle = (start + end) / 2;
            if ((start + end) % 2 == 0) {
                middle = middle + 1;
            }

            //进行递归
            SyncDataTask task1 = new SyncDataTask(start, middle, userService);
            SyncDataTask task2 = new SyncDataTask(middle, end, userService);


            //执行任务
            task1.fork();
            task2.fork();

            //等待任务执行并返回结果
            sum = task1.join() + task2.join();
        } else {
            LOGGER.info("不符合拆分条件:start-{}，end-{}，step-{}", start, end, STEP);
            userService.getUserByIdRange(start, end);
            sum = end;
        }
        return sum;
    }
}
