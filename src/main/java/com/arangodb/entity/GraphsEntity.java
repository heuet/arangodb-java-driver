/*
 * Copyright (C) 2012,2013 tamtam180
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arangodb.entity;

import java.util.List;

/**
 * @author tamtam180 - kirscheless at gmail.com
 * @since 1.4.0
 */
public class GraphsEntity extends BaseEntity {

  List<GraphEntity> graphs;

  public List<GraphEntity> getGraphs() {
    return graphs;
  }

  public void setGraphs(List<GraphEntity> graphs) {
    this.graphs = graphs;
  }
  
}
