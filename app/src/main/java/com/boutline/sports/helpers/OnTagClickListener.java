package com.boutline.sports.helpers;

import com.boutline.sports.models.Tag;

/**
 * listener for tag delete
 */
public interface OnTagClickListener {
	void onTagClick(Tag tag, int position);
}