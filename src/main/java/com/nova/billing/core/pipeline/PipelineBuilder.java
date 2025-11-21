package com.nova.billing.core.pipeline;

import java.util.ArrayList;
import java.util.List;

public class PipelineBuilder {

    private final String name;
    private final List<CalculationStep> steps = new ArrayList<>();

    public PipelineBuilder(String name) {
        this.name = name;
    }

    public PipelineBuilder addStep(CalculationStep step) {
        if (step != null) this.steps.add(step);
        return this;
    }

    public CalculationPipeline build() {
        return new DefaultCalculationPipeline(name, steps);
    }
}
