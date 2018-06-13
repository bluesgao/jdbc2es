package com.bluesgao.jdbc2es.biz;

import com.bluesgao.jdbc2es.Jdbc2esApplication;
import com.bluesgao.jdbc2es.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Jdbc2esApplication.class})
public class SyncDataTaskTest {
    @Resource
    private UserService userService;

    @Test
    public void forkJoinTest() {
        long beginTime = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        SyncDataTask task = new SyncDataTask(0L, 501L, userService);
        ForkJoinTask<Long> result = pool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("耗时=" + (endTime - beginTime));
    }
}