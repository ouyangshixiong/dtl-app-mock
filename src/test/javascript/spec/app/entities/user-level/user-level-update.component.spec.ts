/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { UserLevelUpdateComponent } from 'app/entities/user-level/user-level-update.component';
import { UserLevelService } from 'app/entities/user-level/user-level.service';
import { UserLevel } from 'app/shared/model/user-level.model';

describe('Component Tests', () => {
    describe('UserLevel Management Update Component', () => {
        let comp: UserLevelUpdateComponent;
        let fixture: ComponentFixture<UserLevelUpdateComponent>;
        let service: UserLevelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [UserLevelUpdateComponent]
            })
                .overrideTemplate(UserLevelUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UserLevelUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserLevelService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new UserLevel(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.userLevel = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new UserLevel();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.userLevel = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
