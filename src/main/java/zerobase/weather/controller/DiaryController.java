package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

// Controller 는 Client 로 부터 받은 값을 Service 에게 전달 해주는 역할을 한다.
@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    // Date Type 같은 경우에는 여러가지 포맷이 있으므로 사전 협의가 필요하다
    @PostMapping ("/create/diary")
    @ApiOperation("날씨 데이터를 기반으로 일기를 생성합니다")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "조회할 날짜", example = "2020-02-02")LocalDate date,
                     @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @GetMapping("/read/diary")
    @ApiOperation("지정한 날짜의 일기 중 제일 최근 일기를 가져옵니다.")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          @ApiParam(value = "조회할 날짜", example = "2020-02-02")LocalDate date) {
        return diaryService.readDiary(date);
    }

    @GetMapping("/read/diaries")
    @ApiOperation("지정한 기간의 일기를 모두 가져옵니다.")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 첫번째 날짜", example = "2020-02-02")LocalDate startDate,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                           @ApiParam(value = "조회할 기간의 마지막 날짜", example = "2020-02-02") LocalDate endDate) {

        return diaryService.readDiaries(startDate, endDate);
    }

    @PutMapping("/update/diary")
    @ApiOperation("일기 데이터를 수정합니다.")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "조회할 날짜", example = "2020-02-02")LocalDate date,
                     @RequestBody String text) {

        diaryService.updateDiary(date, text);
    }

    @DeleteMapping("/delete/diary")
    @ApiOperation("일기를 지웁니다.")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "조회할 날짜", example = "2020-02-02")LocalDate date) {

        diaryService.deleteDiary(date);
    }
}
