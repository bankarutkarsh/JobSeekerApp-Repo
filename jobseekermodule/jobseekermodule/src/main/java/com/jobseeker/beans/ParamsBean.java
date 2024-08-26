package com.jobseeker.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamsBean {
	private Long jobseekerId;
	private Long jobId;
}
