import request from "@/utils/request";

// 查询需求管理列表
export function listRequirement(query) {
  return request({
    url: "/kyfz/requirement/list",
    method: "get",
    params: query,
  });
}

// 查询需求管理详细
export function getRequirement(requirementId) {
  return request({
    url: "/kyfz/requirement/" + requirementId,
    method: "get",
  });
}

// 新增需求管理
export function addRequirement(data) {
  return request({
    url: "/kyfz/requirement",
    method: "post",
    data: data,
  });
}

// 修改需求管理
export function updateRequirement(data) {
  return request({
    url: "/kyfz/requirement",
    method: "put",
    data: data,
  });
}

// 删除需求管理
export function delRequirement(requirementId) {
  return request({
    url: "/kyfz/requirement/" + requirementId,
    method: "delete",
  });
}

export function listenterprise(query) {
  return request({
    url: "/kyfz/requirement/listenterprise",
    method: "get",
    params: query,
  });
}


// 新增需求管理
export function pushRequirementId(requirementId) {
  return request({
    url: "/kyfz/requirement/pushRequirementId",
    method: "post",
    data: requirementId,
  });
}