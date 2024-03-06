package org.example.engine;


import org.example.configuration.ITravelListTextCoordinates;

import java.util.List;

public class TravelListTextCoordinates implements ITravelListTextCoordinates {

    @Override
    public List<TextCords> fullNameCoordinates() {
        return List.of(
                new TextCords(1100, 445),
                new TextCords(900, 3010)
        );
    }

    @Override
    public List<TextCords> personalNumberCoordinates() {
        return List.of(
                new TextCords(422, 492)
        );
    }

    @Override
    public List<TextCords> vehicleMakeAndModelCoordinates() {
        return List.of(
                new TextCords(740, 540)
        );
    }

    @Override
    public List<TextCords> fuelConsumptionFor100Coordinates() {
        return List.of(
                new TextCords(1800, 540)
        );
    }

    @Override
    public List<TextCords> vehicleRegNumberCoordinates() {
        return List.of(
                new TextCords(500, 592)
        );
    }

    @Override
    public List<TextCords> fuelTypeCoordinates() {
        return List.of(
                new TextCords(1200, 592)
        );
    }

    @Override
    public List<TextCords> kilometersCoordinates() {
        return List.of(
                new TextCords(1991, 2950)
        );
    }

    @Override
    public List<TextCords> employerNameCoordinates() {
        return List.of(
                new TextCords(900, 3100)
        );
    }

    @Override
    public List<TextCords> monthAndYearCoordinates() {
        return List.of(
                new TextCords(570, 400)
        );
    }

    @Override
    public List<TextCords> days() {
        return List.of(
                new TextCords(320, 933 ),  //day1
                new TextCords(320, 998 ),  //day2
                new TextCords(320, 1063),  //day3
                new TextCords(320, 1125),  //day4
                new TextCords(320, 1188),  //day5
                new TextCords(320, 1250),  //day6
                new TextCords(320, 1313),  //day7
                new TextCords(320, 1377),  //day8
                new TextCords(320, 1439),  //day9
                new TextCords(320, 1502),  //day10
                new TextCords(320, 1562),  //day11
                new TextCords(320, 1625),  //day12
                new TextCords(320, 1688),  //day13
                new TextCords(320, 1750),  //day14
                new TextCords(320, 1810),  //day15
                new TextCords(320, 1875),  //day16
                new TextCords(320, 1937),  //day17
                new TextCords(320, 2000),  //day18
                new TextCords(320, 2063),  //day19
                new TextCords(320, 2125),  //day20
                new TextCords(320, 2189),  //day21
                new TextCords(320, 2251),  //day22
                new TextCords(320, 2315),  //day23
                new TextCords(320, 2376),  //day24
                new TextCords(320, 2438),  //day25
                new TextCords(320, 2502),  //day26
                new TextCords(320, 2563),  //day27
                new TextCords(320, 2623),  //day28
                new TextCords(320, 2688),  //day29
                new TextCords(320, 2748),  //day30
                new TextCords(320, 2811)   //day31
        );
    }

    @Override
    public List<TextCords> destinationAndReasonCoordinates() {
        return List.of(
                new TextCords(792, 933 ),   //day1
                new TextCords(792, 998 ),   //day2
                new TextCords(792, 1063),  //day3
                new TextCords(792, 1125),  //day4
                new TextCords(792, 1188),  //day5
                new TextCords(792, 1250),  //day6
                new TextCords(792, 1313),  //day7
                new TextCords(792, 1377),  //day8
                new TextCords(792, 1439),  //day9
                new TextCords(792, 1502),  //day10
                new TextCords(792, 1562),  //day11
                new TextCords(792, 1625),  //day12
                new TextCords(792, 1688),  //day13
                new TextCords(792, 1750),  //day14
                new TextCords(792, 1810),  //day15
                new TextCords(792, 1875),  //day16
                new TextCords(792, 1937),  //day17
                new TextCords(792, 2000),  //day18
                new TextCords(792, 2063),  //day19
                new TextCords(792, 2125),  //day20
                new TextCords(792, 2189),  //day21
                new TextCords(792, 2251),  //day22
                new TextCords(792, 2315),  //day23
                new TextCords(792, 2376),  //day24
                new TextCords(792, 2438),  //day25
                new TextCords(792, 2502),  //day26
                new TextCords(792, 2563),  //day27
                new TextCords(792, 2623),  //day28
                new TextCords(792, 2688),  //day29
                new TextCords(792, 2748),  //day30
                new TextCords(792, 2811)   //day31
        );
    }

    @Override
    public List<TextCords> kilometersForEachDayCoordinates() {
        return List.of(
                new TextCords(1990, 933 ),   //day1
                new TextCords(1990, 998 ),  //day2
                new TextCords(1990, 1060),  //day3
                new TextCords(1990, 1122),  //day4
                new TextCords(1990, 1185),  //day5
                new TextCords(1990, 1247),  //day6
                new TextCords(1990, 1310),  //day7
                new TextCords(1990, 1374),  //day8
                new TextCords(1990, 1436),  //day9
                new TextCords(1990, 1499),  //day10
                new TextCords(1990, 1559),  //day11
                new TextCords(1990, 1622),  //day12
                new TextCords(1990, 1685),  //day13
                new TextCords(1990, 1750),  //day14
                new TextCords(1990, 1810),  //day15
                new TextCords(1990, 1875),  //day16
                new TextCords(1990, 1937),  //day17
                new TextCords(1990, 1998),  //day18
                new TextCords(1990, 2061),  //day19
                new TextCords(1990, 2123),  //day20
                new TextCords(1990, 2187),  //day21
                new TextCords(1990, 2249),  //day22
                new TextCords(1990, 2313),  //day23
                new TextCords(1990, 2373),  //day24
                new TextCords(1990, 2435),  //day25
                new TextCords(1990, 2499),  //day26
                new TextCords(1990, 2560),  //day27
                new TextCords(1990, 2620),  //day28
                new TextCords(1990, 2685),  //day29
                new TextCords(1990, 2748),  //day30
                new TextCords(1990, 2811)   //day31
        );
    }

    @Override
    public List<TextCords> destinationAndReasonWithNightStayCoordinates() {
        return List.of(
                new TextCords(792, 933 ),   //day1
                new TextCords(792, 998 )   //day2
        );
    }

    @Override
    public List<TextCords> kilometersForEachDayWithNightStayCoordinates() {
        return List.of(
                new TextCords(1990, 933 ),   //day1
                new TextCords(1990, 998 )  //day2
        );
    }
}
