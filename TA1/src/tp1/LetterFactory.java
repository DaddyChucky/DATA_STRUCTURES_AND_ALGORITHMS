package tp1;

interface TranslateShape {
    static BaseShape translate(BaseShape shape, Double offsetX, Double offsetY) {
        if (offsetX != null)
            shape.translate(new Point2d(offsetX, 0.0));

        if (offsetY != null)
            shape.translate(new Point2d(0.0, offsetY));

        return shape;
    }
}

interface RotateShape {
    static BaseShape rotate(BaseShape shape, Double rotationAngle) {
        if (rotationAngle != null)
            shape.rotate(rotationAngle);

        return shape;
    }
}

interface CreateRectangle extends TranslateShape, RotateShape {
    static Rectangle create(Double width, Double height, Double offsetX, Double offsetY,
                                    Double rotationAngle) {
        Rectangle rectangle = new Rectangle(width, height);

        TranslateShape.translate(rectangle, offsetX, offsetY);
        RotateShape.rotate(rectangle, rotationAngle);

        return rectangle;
    }
}

interface CreateEllipse extends TranslateShape, RotateShape {
    static Ellipse create(Double width, Double height, Double offsetX, Double offsetY,
                                  Double rotationAngle) {
        Ellipse ellipse = new Ellipse(width, height);

        RotateShape.rotate(TranslateShape.translate(ellipse, offsetX, offsetY), rotationAngle);

        return ellipse;
    }
}

interface AddEllipse extends CreateEllipse {
    static BaseShape add(BaseShape shape, Double width, Double height, Double offsetX,
                            Double offsetY, Double rotationAngle) {
        shape.add(CreateEllipse.create(width, height, offsetX, offsetY, rotationAngle));

        return shape;
    }
}

interface RemoveEllipse extends CreateEllipse {
    // Although never used, might be useful for creating other shapes!
    static BaseShape remove(BaseShape shape, Double width, Double height, Double offsetX,
                               Double offsetY, Double rotationAngle) {
        shape.remove(CreateEllipse.create(width, height, offsetX, offsetY, rotationAngle));

        return shape;
    }
}

interface AddRectangle extends CreateRectangle {
    static BaseShape add(BaseShape shape, Double width, Double height, Double offsetX,
                            Double offsetY, Double rotationAngle) {
        shape.add(CreateRectangle.create(width, height, offsetX, offsetY, rotationAngle));

        return shape;
    }
}

interface RemoveRectangle extends CreateRectangle {
    static BaseShape remove(BaseShape shape, Double width, Double height, Double offsetX,
                               Double offsetY, Double rotationAngle) {
        shape.remove(CreateRectangle.create(width, height, offsetX, offsetY, rotationAngle));

        return shape;
    }
}

public final class LetterFactory implements AddRectangle, RemoveRectangle, AddEllipse, RemoveEllipse {
    final static Integer firstVRectangleAngleInDegrees = 170;
    final static Integer secondVRectangleAngleInDegrees = firstVRectangleAngleInDegrees + 20;
    final static Double maxHeight = 200.0;
    final static Double maxWidth = maxHeight / 2.5;
    final static Double stripeThickness = maxHeight / 8;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double fourPointFiveTimesMaxHeight = maxHeight * 4/5;
    final static Double thirteenTwelvesMaxHeight = maxHeight * 13/12;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double twoThirdsMaxWidth = maxWidth * 2/3;
    final static Double nineTenthsHalfMaxWidth = halfMaxWidth * 9 / 10;
    final static Double eightTenthsHalfMaxWidth = halfMaxWidth * 8 / 10;
    final static Double fourFifthsHalfMaxWidth = halfMaxWidth * 4/5;
    final static Double onePointThreeTimesHalfMaxWidth = halfMaxWidth * 1.3;
    final static Double onePointTwoTimesHalfMaxWidth = halfMaxWidth * 1.2;
    final static Double onePointFourTimesHalfMaxWidth = halfMaxWidth * 1.4;
    final static Double onePointTwentyTwoTimesHalfMaxWidth = halfMaxWidth  * 1.22;
    final static Double onePointThirtyTwoHalfMaxWidth = halfMaxWidth  * 1.32;
    final static Double onePointFortyTwoTimesHalfMaxWidth = halfMaxWidth  * 1.42;
    final static Double oneInEightyTimesHalfMaxHeight = halfMaxHeight / 80;
    final static Double thirteenFourteensHalfMaxHeight = halfMaxHeight * 13 /14;
    final static Double twelveThirteenthsHalfMaxHeight = halfMaxHeight * 12 / 13;
    final static Double sixPointThirtyFiveTimesHalfMaxHeight = halfMaxHeight / 6.35;
    final static Double oneThirdHalfMaxHeight = halfMaxHeight / 3;
    final static Double oneQuarterMaxHeight = halfMaxHeight/2;
    final static Double oneFourthsMaxHeight = halfMaxHeight/2;
    final static Double oneSixthsMaxHeight = halfMaxHeight / 3;
    final static Double halfStripeThickness = stripeThickness / 2;
    final static Double twoPointSevenTimesStripeThickness = 2.7 * stripeThickness;
    final static Double twoThirdsStripeThickness = stripeThickness * 2 /3;
    final static Double tripleHalfStripeThickness = halfStripeThickness * 3;
    final static Double fourFifthsHalfStripeThickness = halfStripeThickness * 4/5;
    final static Double oneQuarterStripeThickness = halfStripeThickness / 2;
    final static Double oneTenthsStripeThickness = halfStripeThickness / 5;
    final static Double oneSixthsStripeThickness = halfStripeThickness/3;
    final static Double oneEightsStripeThickness = oneQuarterStripeThickness / 2;
    final static Double onePointOneTimesHalfMaxHeight = halfMaxHeight * 1.1;

    private LetterFactory() {}  // Evade outside function instance -> Réf: https://github.com/MilindAtGithub/fluentbuilder/blob/master/src/main/java/com/milind/fluentbuilder/Email.java
    private static BaseShape getInstance() { return new BaseShape(); }

    public static BaseShape create_e() {
        return
                RemoveRectangle.remove
                        (
                                AddEllipse.add
                                        (
                                                AddEllipse.add
                                                        (
                                                                AddEllipse.add
                                                                        (
                                                                                AddEllipse.add
                                                                                        (
                                                                                                AddRectangle.add
                                                                                                        (
                                                                                                                getInstance(), maxWidth, twoThirdsStripeThickness, -halfMaxWidth, -oneInEightyTimesHalfMaxHeight, null
                                                                                                        ),
                                                                                                halfMaxWidth, halfMaxHeight, null, null, null
                                                                                        ),
                                                                                nineTenthsHalfMaxWidth, thirteenFourteensHalfMaxHeight, null, null, null
                                                                        ),
                                                                eightTenthsHalfMaxWidth, twelveThirteenthsHalfMaxHeight, null, null, null
                                                        ),
                                                eightTenthsHalfMaxWidth, twelveThirteenthsHalfMaxHeight, null, null, null
                                        ),
                                maxWidth, tripleHalfStripeThickness, null, sixPointThirtyFiveTimesHalfMaxHeight, null
                        );
    }

    public static BaseShape create_a() {
        return
                AddRectangle.add
                        (
                        AddEllipse.add
                                (
                                AddEllipse.add
                                        (
                                        AddEllipse.add
                                                (
                                                new BaseShape(), halfMaxWidth, halfMaxHeight, null, null, null
                                                ),
                                                nineTenthsHalfMaxWidth, thirteenFourteensHalfMaxHeight, null, null, null
                                        ),
                                        eightTenthsHalfMaxWidth, twelveThirteenthsHalfMaxHeight, null, null, null
                                ),
                                halfStripeThickness, maxHeight, fourFifthsHalfMaxWidth, -halfMaxHeight, null
                        );
    }

    public static BaseShape create_C() { return(new BaseShape()); }

    public static BaseShape create_l()  {
        return AddRectangle.add(new BaseShape(), halfStripeThickness, maxHeight, null, null, null);
    }

    public static BaseShape create_i() {
        return
                AddRectangle.add
                        (
                        AddRectangle.add
                                (
                                        AddEllipse.add
                                                (
                                                new BaseShape(), fourFifthsHalfStripeThickness, fourFifthsHalfStripeThickness, oneQuarterStripeThickness, null, null
                                                ),
                                        fourFifthsHalfStripeThickness, fourFifthsHalfStripeThickness, oneEightsStripeThickness, -oneTenthsStripeThickness, null
                                ),
                                halfStripeThickness, fourPointFiveTimesMaxHeight, null, halfMaxHeight - twoPointSevenTimesStripeThickness, null
                        );
    }

    public static BaseShape create_A() {
        return create_a();
    }

    public static BaseShape create_V() {
        return
                AddRectangle.add
                        (
                                AddRectangle.add
                                        (
                                                new BaseShape(), halfStripeThickness, maxHeight, null, null, Math.toRadians(firstVRectangleAngleInDegrees)
                                        ),
                                halfStripeThickness, maxHeight, null, null, Math.toRadians(secondVRectangleAngleInDegrees)
                        );
    }

    public static BaseShape create_n() {
        return(new BaseShape());
    }

    public static BaseShape create_r() {
        return(new BaseShape());
    }

    public static BaseShape create_B() {
        return
            AddRectangle.add
                    (
                            RemoveRectangle.remove
                                    (
                                            AddEllipse.add
                                                    (
                                                            AddEllipse.add
                                                                    (
                                                                            AddEllipse.add
                                                                                    (
                                                                                            AddEllipse.add
                                                                                                    (
                                                                                                            AddEllipse.add
                                                                                                                    (
                                                                                                                            AddEllipse.add
                                                                                                                                    (
                                                                                                                                            new BaseShape(), onePointFourTimesHalfMaxWidth, (onePointFortyTwoTimesHalfMaxWidth), null, -oneQuarterMaxHeight - oneSixthsStripeThickness, null
                                                                                                                                    ),
                                                                                                                            onePointThreeTimesHalfMaxWidth, (onePointThirtyTwoHalfMaxWidth), null, -oneFourthsMaxHeight - oneSixthsStripeThickness, null
                                                                                                                    ),
                                                                                                            onePointTwoTimesHalfMaxWidth, (onePointTwentyTwoTimesHalfMaxWidth), null, -oneFourthsMaxHeight - oneSixthsStripeThickness, null
                                                                                                    ),

                                                                                            onePointFourTimesHalfMaxWidth, (onePointFortyTwoTimesHalfMaxWidth), null, oneSixthsMaxHeight, null
                                                                                    ),
                                                                            onePointThreeTimesHalfMaxWidth, (onePointThirtyTwoHalfMaxWidth), null, oneThirdHalfMaxHeight, null
                                                                    ),
                                                            onePointTwoTimesHalfMaxWidth, (onePointTwentyTwoTimesHalfMaxWidth), null, oneThirdHalfMaxHeight, null
                                                    ),
                                            twoThirdsMaxWidth, thirteenTwelvesMaxHeight, -onePointFourTimesHalfMaxWidth, -halfMaxHeight - halfStripeThickness, null
                                    ),
                            twoThirdsStripeThickness, maxHeight, -oneEightsStripeThickness, -onePointOneTimesHalfMaxHeight, null
                    );
    }
}
