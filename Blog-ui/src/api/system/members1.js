import request from '@/utils/request'

// 查询队伍成员列表
export function listMembers1(query) {
  return request({
    url: '/system/members1/list',
    method: 'get',
    params: query
  })
}

// 查询队伍成员详细
export function getMembers1(id) {
  return request({
    url: '/system/members1/' + id,
    method: 'get'
  })
}

// 新增队伍成员
export function addMembers1(data) {
  return request({
    url: '/system/members1',
    method: 'post',
    data: data
  })
}

// 修改队伍成员
export function updateMembers1(data) {
  return request({
    url: '/system/members1',
    method: 'put',
    data: data
  })
}

// 删除队伍成员
export function delMembers1(id) {
  return request({
    url: '/system/members1/' + id,
    method: 'delete'
  })
}
