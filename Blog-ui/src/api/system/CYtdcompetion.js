import request from '@/utils/request'

// 查询成员团队赛事列表
export function listCYtdcompetion(query) {
  return request({
    url: '/system/CYtdcompetion/list',
    method: 'get',
    params: query
  })
}

// 查询成员团队赛事详细
export function getCYtdcompetion(competitionId) {
  return request({
    url: '/system/CYtdcompetion/' + competitionId,
    method: 'get'
  })
}

// 新增成员团队赛事
export function addCYtdcompetion(data) {
  return request({
    url: '/system/CYtdcompetion',
    method: 'post',
    data: data
  })
}

// 修改成员团队赛事
export function updateCYtdcompetion(data) {
  return request({
    url: '/system/CYtdcompetion',
    method: 'put',
    data: data
  })
}

// 删除成员团队赛事
export function delCYtdcompetion(competitionId) {
  return request({
    url: '/system/CYtdcompetion/' + competitionId,
    method: 'delete'
  })
}
